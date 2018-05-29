/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.pojo.modle.ProvidentFundDO;
import com.platform.loan.pojo.modle.SocialSecurityDO;
import com.platform.loan.pojo.request.CallBackRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.util.LoanLogUtil;
import com.platform.loan.util.TimeUtil;
import com.platform.loan.util.http.LoanHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  https://docs.51datakey.com/docs/fund/302#task_submit
 * @author caogu.wyp
 * @version $Id: CallBackController.java, v 0.1 2018-05-18 下午10:44 caogu.wyp Exp $$
 */
@RestController
public class CallBackController {

    @Autowired
    private ProvidentFundRepository  providentFundRepository;

    @Autowired
    private SocialSecurityRepository socialSecurityRepository;

    @RequestMapping(value = "/callback/providentfund/submit", method = RequestMethod.POST)
    public BaseResult providentFundSubmit(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {
        //授权申请成功
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/finish", method = RequestMethod.POST)
    public BaseResult providentFundFinish(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {

        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/fail", method = RequestMethod.POST)
    public BaseResult providentFundFail(@RequestBody CallBackRequest callBackRequest,
                                        HttpServletResponse response) {
        //数据采集失败
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/bill", method = RequestMethod.POST)
    public void providentFundBill(@RequestBody CallBackRequest callBackRequest,
                                  HttpServletResponse response) throws IOException {
        //数据采集完成
        LoanLogUtil.getLogger(CallBackController.class).info("/callback/providentfund/bill",
            callBackRequest);

        /**
         * 1、再通过taskId去查用户公积金信息，拿到结果后
         * https://docs.51datakey.com/docs/fund/fund_origin
         * 2、先根据手机号（user_id）找到用户的公积金记录，ProvidentFundDO，没有则插入
         */
        String taskId = callBackRequest.getTask_id();
        String url = "https://api.51datakey.com/fund/v2/funds-ex/" + taskId;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "token ccf53f9f62e9405a85c3cb037e7a97c4");
        String jsonResponse = LoanHttpUtil.sendGet(url, headers);
        JSONObject object = JSONArray.parseObject(jsonResponse);
        System.out.println(jsonResponse);

        if (StringUtils.isBlank(object.getString("task_id"))) {
            LoanLogUtil.getLogger(CallBackController.class).error(
                "/callback/providentfund/bill，收到回调请求时，拿taskId去公积金中心查询失败，请求参数为："
                        + callBackRequest.toString(), object);
            return;
        }

        ProvidentFundDO providentFundDO = providentFundRepository
            .findProvidentFundDoByPhoneNo(callBackRequest.getUser_id());
        if (null != providentFundDO) {
            providentFundDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            providentFundDO.setPhoneNo(callBackRequest.getUser_id());
            providentFundDO.setFundDetail(jsonResponse);
            providentFundRepository.save(providentFundDO);
        } else {
            ProvidentFundDO newProvidentFundDO = new ProvidentFundDO();
            newProvidentFundDO.setFundDetail(jsonResponse);
            newProvidentFundDO.setPhoneNo(callBackRequest.getUser_id());
            newProvidentFundDO.setCreateTime(TimeUtil.getCurrentTimestamp());
            newProvidentFundDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            providentFundRepository.save(newProvidentFundDO);
        }

        //最后才响应成功
        response.setStatus(201);
    }

    @RequestMapping(value = "/callback/providentfund/report", method = RequestMethod.POST)
    public BaseResult providentFundReport(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    //=============//
    @RequestMapping(value = "/callback/socialsecurity/submit", method = RequestMethod.POST)
    public BaseResult socialSecuritySubmit(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/finish", method = RequestMethod.POST)
    public BaseResult socialSecurityFinish(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/fail", method = RequestMethod.POST)
    public BaseResult socialSecurityFail(@RequestBody CallBackRequest callBackRequest,
                                         HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/bill", method = RequestMethod.POST)
    public void socialSecurityBill(@RequestBody CallBackRequest callBackRequest,
                                   HttpServletResponse response) throws IOException {
        //数据采集完成
        LoanLogUtil.getLogger(CallBackController.class).info("/callback/socialsecurity/bill",
            callBackRequest);

        String taskId = callBackRequest.getTask_id();
        String url = "https://api.51datakey.com/security/v1/result/" + taskId;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "token ccf53f9f62e9405a85c3cb037e7a97c4");
        String jsonResponse = LoanHttpUtil.sendGet(url, headers);
        JSONObject object = JSONArray.parseObject(jsonResponse);
        System.out.println(jsonResponse);

        if (StringUtils.isBlank(object.getString("task_id"))) {
            LoanLogUtil.getLogger(CallBackController.class).error(
                "/callback/socialsecurity/bill 查询账单失败，请求参数为：" + callBackRequest.toString(), object);
            return;
        }

        SocialSecurityDO socialSecurityDO = socialSecurityRepository
            .findSocialSecurityDoByPhoneNo(callBackRequest.getUser_id());
        if (null != socialSecurityDO) {
            socialSecurityDO.setPhoneNo(callBackRequest.getUser_id());
            socialSecurityDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            socialSecurityDO.setSocialDetail(jsonResponse);
            socialSecurityRepository.save(socialSecurityDO);
        } else {
            SocialSecurityDO newSocialSecurityDO = new SocialSecurityDO();
            newSocialSecurityDO.setPhoneNo(callBackRequest.getUser_id());
            newSocialSecurityDO.setCreateTime(TimeUtil.getCurrentTimestamp());
            newSocialSecurityDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            newSocialSecurityDO.setSocialDetail(jsonResponse);
            socialSecurityRepository.save(newSocialSecurityDO);
        }
        //最后才响应成功
        response.setStatus(201);
    }

    @RequestMapping(value = "/callback/socialsecurity/report", method = RequestMethod.POST)
    public BaseResult socialSecurityReport(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

}
