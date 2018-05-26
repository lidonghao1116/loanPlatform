/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.request.UpdateManagerInfoRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.Processor;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: UpdateManagerInfoProcessor.java, v 0.1 2018-05-24 下午11:46 caogu.wyp Exp $$
 */
public class UpdateManagerInfoProcessor implements Processor<UpdateManagerInfoRequest, BaseResult> {
    @Override
    public void process(UpdateManagerInfoRequest updateManagerInfoRequest, BaseResult result,
                        Object... others) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        ManagerRepository managerRepository = (ManagerRepository) others[1];

        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        CreditManagerDO creditManagerDO = managerRepository
            .findCreditManagerDOByPhoneNo(loginSession.getPhoneNo());

        if (null == creditManagerDO) {
            throw new LoanPlatformException("数据库不存在此账号!" + loginSession.getPhoneNo());
        }

        creditManagerDO.setCity(updateManagerInfoRequest.getCity());
        creditManagerDO.setCompany(updateManagerInfoRequest.getCompany());
        creditManagerDO.setWebChatNo(updateManagerInfoRequest.getWebChatNo());
        managerRepository.save(creditManagerDO);

    }
}
