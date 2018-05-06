/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.constant.SystemConfigurationEnum;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.request.OTPRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.service.OtpService;
import com.platform.loan.util.RequestCheckUtil;
import com.platform.loan.util.SystemConfigurationUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  短信验证码
 * @author caogu.wyp
 * @version $Id: OTPController.java, v 0.1 2018-05-05 下午8:44 caogu.wyp Exp $$
 */
@RestController
public class OTPController {

    @Autowired
    private OtpService otpService;

    @ApiOperation(value = "发送手机验证码", notes = "给手机发送验证码，用于登录")
    @RequestMapping(value = "/sms/send", method = RequestMethod.POST)
    public BaseResult sendOtp(OTPRequest otpRequst) {

        BaseResult result = new BaseResult();

        try {
            RequestCheckUtil.checkOtpRequst(otpRequst);

            String SMSCode = getSMSCode();
            otpService.sendOtp(otpRequst.getPhoneNo(), getSMSMessage(SMSCode));
            //放缓存中等登录时取出来验证
            SimpleCacheUtil.addSMSCode(otpRequst.getPhoneNo(), SMSCode);

        } catch (LoanPlatformException e) {
            e.printStackTrace();
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }

    private String getSMSCode() {

        int random = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(random);

    }

    private String getSMSMessage(String SMSCode) {
        StringBuilder sb = new StringBuilder();

        sb.append(SystemConfigurationUtil
            .getSystemConfigurationValueByKey(SystemConfigurationEnum.CHUANG_LAN_SMS_TEMP));
        //验证码随机6位数

        sb.append(String.valueOf(SMSCode));

        return sb.toString();
    }

}
