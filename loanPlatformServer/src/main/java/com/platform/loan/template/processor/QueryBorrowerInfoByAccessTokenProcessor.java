/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.ProvidentFundDO;
import com.platform.loan.pojo.modle.SocialSecurityDO;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BorrowerInfoResult;
import com.platform.loan.template.Processor;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author caogu.wyp
 * @version $Id: QueryBorrowerInfoByAccessTokenProcessor.java, v 0.1 2018-05-12 下午3:28 caogu.wyp Exp $$
 */
public class QueryBorrowerInfoByAccessTokenProcessor implements
                                                    Processor<BaseRequest, BorrowerInfoResult> {
    @Override
    public void process(BaseRequest request, BorrowerInfoResult borrowerInfoResult,
                        Object... others) throws UnsupportedEncodingException {

        HttpServletRequest httpRequest = (HttpServletRequest) others[0];
        BorrowerRepository borrowerRepository = (BorrowerRepository) others[1];
        ProvidentFundRepository providentFundRepository = (ProvidentFundRepository) others[2];
        SocialSecurityRepository socialSecurityRepository = (SocialSecurityRepository) others[3];

        LoginSession loginSession = JwtUtil.getLoginSession(httpRequest);

        initResult(loginSession, borrowerInfoResult, borrowerRepository, providentFundRepository,
            socialSecurityRepository);

    }

    private void initResult(LoginSession loginSession, BorrowerInfoResult result,
                            BorrowerRepository borrowerRepository,
                            ProvidentFundRepository providentFundRepository,
                            SocialSecurityRepository socialSecurityRepository) {

        BorrowerDO borrowerDo = borrowerRepository.findBorrowerDoByPhoneNo(loginSession
            .getPhoneNo());

        if (null == borrowerDo) {
            return;
        }
        result.setPhoneNo(borrowerDo.getPhoneNo());
        result.setIdNo(borrowerDo.getIdNo());
        result.setName(borrowerDo.getName());

        //====查询公积金认证情况
        ProvidentFundDO providentFundDO = providentFundRepository
            .findProvidentFundDoByPhoneNo(borrowerDo.getPhoneNo());
        if (null != providentFundDO) {

            String jsonStr = providentFundDO.getExtData();
            JSONObject object = JSONArray.parseObject(jsonStr);
            if (null != object) {
                result.setProvidentFundCity(object.getString("city"));
            }
            if (null != providentFundDO.getCreateTime()) {
                result.setProvidentFundVerifyTime(providentFundDO.getCreateTime().toString());
            }
        }
        //====查询社保认证情况
        SocialSecurityDO socialSecurityDO = socialSecurityRepository
            .findSocialSecurityDoByPhoneNo(borrowerDo.getPhoneNo());
        if (null != socialSecurityDO) {
            String jsonStr = socialSecurityDO.getExtData();
            JSONObject object = JSONArray.parseObject(jsonStr);

            if (null != object) {
                result.setSocialSecurityCity(object.getString("city"));
            }
            if (null != socialSecurityDO.getCreateTime()) {
                result.setSocialSecurityVerifyTime(socialSecurityDO.getCreateTime().toString());
            }
        }

    }
}
