/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDO;
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

        LoginSession loginSession = JwtUtil.getLoginSession(httpRequest);

        initResult(loginSession, borrowerInfoResult, borrowerRepository);

    }

    private void initResult(LoginSession loginSession, BorrowerInfoResult result,
                            BorrowerRepository borrowerRepository) {

        BorrowerDO borrowerDo = borrowerRepository.findBorrowerDoByPhoneNo(loginSession
            .getPhoneNo());

        if (null == borrowerDo) {
            return;
        }
        result.setPhoneNo(borrowerDo.getPhoneNo());
        result.setIdNo(borrowerDo.getIdNo());
        result.setName(borrowerDo.getName());

        if (null != borrowerDo.getProvidentFundVerifyTime()) {

            result.setProvidentFundVerifyTime(borrowerDo.getProvidentFundVerifyTime().toString());
        }

        if (null != borrowerDo.getSocialSecurityVerifyTime()) {
            result.setSocialSecurityVerifyTime(borrowerDo.getSocialSecurityVerifyTime().toString());
        }

    }
}
