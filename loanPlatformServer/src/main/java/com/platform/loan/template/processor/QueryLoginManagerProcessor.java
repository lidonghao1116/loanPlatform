/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.QueryLoginManagerResult;
import com.platform.loan.template.Processor;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: QueryLoginManagerProcessor.java, v 0.1 2018-05-24 上午1:06 caogu.wyp Exp $$
 */
public class QueryLoginManagerProcessor implements Processor<BaseRequest, QueryLoginManagerResult> {
    @Override
    public void process(BaseRequest baseRequest, QueryLoginManagerResult queryLoginManagerResult,
                        Object... others) throws Exception {

        HttpServletRequest request = (HttpServletRequest) others[0];
        ManagerRepository managerRepository = (ManagerRepository) others[1];

        LoginSession loginSession = JwtUtil.getLoginSession(request);

        CreditManagerDO creditManagerDO = managerRepository
            .findCreditManagerDOByPhoneNo(loginSession.getPhoneNo());
        if (null == creditManagerDO) {
            throw new LoanPlatformException(ResultCodeEnum.TOKEN_VERIFY_FAILED, "数据库无账号信息，请重新登陆");
        }

        queryLoginManagerResult.setBalance(creditManagerDO.getBalance());
        queryLoginManagerResult.setCity(creditManagerDO.getCity());
        queryLoginManagerResult.setCompany(creditManagerDO.getCompany());
        queryLoginManagerResult.setPhoneNo(creditManagerDO.getPhoneNo());
        queryLoginManagerResult.setWebChatNo(creditManagerDO.getWebChatNo());
    }
}
