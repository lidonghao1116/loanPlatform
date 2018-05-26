/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.LoginUserTypeEnum;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.request.ManagerLoginRequest;
import com.platform.loan.pojo.result.LoginResult;
import com.platform.loan.service.common.CommonMethod;
import com.platform.loan.template.Processor;
import com.platform.loan.util.RequestCheckUtil;
import com.platform.loan.util.TimeUtil;

/**
 * @author caogu.wyp
 * @version $Id: ManagerLoginProcessor.java, v 0.1 2018-05-22 上午12:51 caogu.wyp Exp $$
 */
public class ManagerLoginProcessor implements Processor<ManagerLoginRequest, LoginResult> {
    @Override
    public void process(ManagerLoginRequest managerLoginRequest, LoginResult loginResult,
                        Object... others) throws Exception {

        ManagerRepository managerRepository = (ManagerRepository) others[0];

        //请求参数判空
        RequestCheckUtil.checkManagerLoginRequest(managerLoginRequest);
        //校验短信
        CommonMethod.verifyOTP(managerLoginRequest.getPhoneNo(),
            LoginUserTypeEnum.CREDIT_MANAGER.getCode(), managerLoginRequest.getSmsCode());
        //更新用户信息
        updateManagerInfo(managerLoginRequest, managerRepository);
        //下发token
        loginResult.setAccessToken(CommonMethod.buildLoginAccessToken(
            managerLoginRequest.getPhoneNo(), LoginUserTypeEnum.CREDIT_MANAGER.getCode()));
    }

    private void updateManagerInfo(ManagerLoginRequest managerLoginRequest,
                                   ManagerRepository managerRepository) {

        if (null == managerRepository
            .findCreditManagerDOByPhoneNo(managerLoginRequest.getPhoneNo())) {

            CreditManagerDO creditManagerDO = new CreditManagerDO();
            creditManagerDO.setPhoneNo(managerLoginRequest.getPhoneNo());
            creditManagerDO.setCreateTime(TimeUtil.getCurrentTimestamp());
            creditManagerDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            managerRepository.save(creditManagerDO);
        }
    }
}
