/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.constant.ResultCode;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 借款人接口
 * @author caogu.wyp 
 */
@RestController
public class BorrowerController {

    @ApiOperation(value = "借款人登录接口", notes = "输入手机号，短信验证码，图片验证码，验证成功即可登录")
    //@ApiImplicitParam(name = "borrowerLoginRequest", value = "borrowerLoginRequest", required = true, dataType = "BorrowerLoginRequest")
    @RequestMapping(value = "/borrowers/login", method = RequestMethod.POST)
    public BaseResult login(BorrowerLoginRequest borrowerLoginRequest) {

        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.TRUE.toString());
        result.setResultCode(ResultCode.SUCCESS.getCode());

        result.setResultMessage(borrowerLoginRequest.toString());
        return result;
    }

}
