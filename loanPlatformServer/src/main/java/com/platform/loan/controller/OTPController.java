package com.platform.loan.controller;

import com.platform.loan.pojo.request.OTPRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.service.OtpService;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.OtpSendProcessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  短信验证码
 * @author caogu.wyp
 * @version $Id: OTPController.java, v 0.1 2018-05-05 下午8:44 caogu.wyp Exp $$
 */
@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @ApiOperation(value = "发送手机验证码", notes = "给手机发送验证码，用于登录")
    @RequestMapping(value = "/api/sms/send", method = RequestMethod.POST)
    public BaseResult sendOtp(@RequestBody OTPRequest otpRequest) {

        return LoanPlatformTemplate.run(new OtpSendProcessor(), otpRequest, new BaseResult(),
            otpService);

    }

}
