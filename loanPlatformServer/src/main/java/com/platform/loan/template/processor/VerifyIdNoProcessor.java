/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.alibaba.fastjson.JSONObject;
import com.platform.loan.constant.LoginUserTypeEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.VerifyIdNoModel;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.request.VerifyIdNoRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanLogUtil;
import com.platform.loan.util.http.LoanHttpUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author caogu.wyp
 * @version $Id: VerifyIdNoProcessor.java, v 0.1 2018-05-17 下午11:29 caogu.wyp Exp $$
 */
public class VerifyIdNoProcessor implements Processor<VerifyIdNoRequest, BaseResult> {

    private static String fformatStr = "/dsp-front/4.1/dsp-front/default/pubkey/%s/"
                                       + "product_code/%s/out_order_id/%s/signature/%s";

    @Override
    public void process(VerifyIdNoRequest request, BaseResult result, Object... others)
                                                                                       throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];

        BorrowerRepository borrowerRepository = (BorrowerRepository) others[1];

        ManagerRepository managerRepository = (ManagerRepository) others[2];

        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        if (LoginUserTypeEnum.CREDIT_MANAGER.getCode().equals(loginSession.getLoginUserType())) {

            CreditManagerDO creditManagerDO = managerRepository
                .findCreditManagerDOByPhoneNo(loginSession.getPhoneNo());

            if (null == creditManagerDO) {
                throw new Exception("未在数据库中找到该用户，请重新正常流程登录！");
            }

            if (verifyPass(request)) {

                creditManagerDO.setIdNo(request.getIdNo());
                creditManagerDO.setName(request.getName());
                managerRepository.save(creditManagerDO);
            }
        } else if (LoginUserTypeEnum.BORROWER.getCode().equals(loginSession.getLoginUserType())) {
            BorrowerDO borrowerDo = borrowerRepository.findBorrowerDoByPhoneNo(loginSession
                .getPhoneNo());

            if (null == borrowerDo) {

                throw new Exception("未在数据库中找到该用户，请重新正常流程登录！");
            }
            if (verifyPass(request)) {

                borrowerDo.setIdNo(request.getIdNo());
                borrowerDo.setName(request.getName());
                borrowerRepository.save(borrowerDo);
            }

        } else {

            throw new Exception("未知登陆类型：" + loginSession);
        }

    }

    private boolean verifyPass(VerifyIdNoRequest request) throws IOException,
                                                         NoSuchAlgorithmException {

        String url = "https://api4.udcredit.com";
        VerifyIdNoModel model = new VerifyIdNoModel();
        model.setId_name(request.getName());
        model.setId_no(request.getIdNo());
        String bodyStr = JSONObject.toJSONString(model);
        String signature = md5(bodyStr + "|" + "2b2fcaf0-9c75-48a0-b53b-af56f5415c0f");
        url += String.format(fformatStr, "d0f5910c-3cf2-4cf5-8643-1dc6adc8cd19", "O1001S0201",
            System.currentTimeMillis(), signature);
        LoanLogUtil.getLogger(VerifyIdNoProcessor.class).info("requestUrl=>" + url);
        LoanLogUtil.getLogger(VerifyIdNoProcessor.class).info("request parameter body=>" + bodyStr);
        String jsonRespon = LoanHttpUtil.sendPost(url, bodyStr);
        LoanLogUtil.getLogger(VerifyIdNoProcessor.class).info("身份认证请求结果：" + jsonRespon);
        JSONObject jsonObject = JSONObject.parseObject(jsonRespon);
        String resultCode = jsonObject.getJSONObject("header").getString("ret_code");

        if ("000000".equals(resultCode)) {

            String status = jsonObject.getJSONObject("body").getString("status");

            //请求成功
            /**
             * 认证结果状态码：
             1-认证一致，
             2-认证不一致，
             3-无结果（查询不到此身份证号）
             */
            if ("1".equals(status)) {
                return true;

            } else {
                throw new LoanPlatformException("身份认证失败");
            }

        } else {
            throw new LoanPlatformException(jsonObject.getJSONObject("header").getString("ret_msg"));
        }

    }

    /**
     *字节转换为16进制字符串
     */
    private static String byteToHex(byte ch) {
        String str[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
                "F" };
        return str[ch >> 4 & 0xF] + str[ch & 0xF];
    }

    private static String bytesToHex(byte[] ch) {
        StringBuffer ret = new StringBuffer("");
        for (int i = 0; i < ch.length; i++) {
            ret.append(byteToHex(ch[i]));
        }
        return ret.toString();
    }

    private static String md5(String data) throws NoSuchAlgorithmException,
                                          UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.toString().getBytes("utf-8"));
        return bytesToHex(md.digest());
    }

}
