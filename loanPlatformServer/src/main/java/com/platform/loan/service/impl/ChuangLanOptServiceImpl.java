/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.loan.constant.SystemConfigurationEnum;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.service.OtpService;
import com.platform.loan.util.SystemConfigurationUtil;
import com.platform.loan.util.lanchuang.ChuangLanSmsUtil;
import com.platform.loan.util.lanchuang.SmsSendRequest;
import com.platform.loan.util.lanchuang.SmsSendResponse;
import org.springframework.stereotype.Service;

/**
 *
 * @author caogu.wyp
 * @version $Id: ChuangLanOptServiceImpl.java, v 0.1 2018-05-06 上午12:14 caogu.wyp Exp $$
 */
@Service
public class ChuangLanOptServiceImpl implements OtpService {

    /**
     * @param phoneNo
     * @return
     */
    @Override
    public void sendOtp(String phoneNo, String SMSMessage) throws LoanPlatformException {

        SmsSendRequest smsSingleRequest = new SmsSendRequest(
            SystemConfigurationUtil
                .getSystemConfigurationValueByKey(SystemConfigurationEnum.CHUANG_LAN_ACCOUNT),
            SystemConfigurationUtil
                .getSystemConfigurationValueByKey(SystemConfigurationEnum.CHUANG_LAN_PASSWORD),
            SMSMessage, phoneNo, "true");

        String requestJson = JSON.toJSONString(smsSingleRequest);

        String response = ChuangLanSmsUtil.sendSmsByPost(SystemConfigurationUtil
            .getSystemConfigurationValueByKey(SystemConfigurationEnum.CHUANG_LAN_URL), requestJson);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        if (!"0".equals(smsSingleResponse.getCode())) {

            throw new LoanPlatformException(smsSingleResponse.getErrorMsg());
        }

        System.out.println("response  toString is :" + smsSingleResponse);

    }
}
