/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.constant.ResultCode;
import com.platform.loan.pojo.request.OTPRequest;
import com.platform.loan.pojo.result.BaseResult;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "发送手机验证码", notes = "给手机发送验证码，用于登录")
    @RequestMapping(value = "/sms/send", method = RequestMethod.POST)
    public BaseResult sendOtp(OTPRequest otpRequst) {

        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.FALSE.toString());
        result.setResultCode(ResultCode.SUCCESS.getCode());

        result.setResultMessage(otpRequst.toString());

        return result;
    }

}
