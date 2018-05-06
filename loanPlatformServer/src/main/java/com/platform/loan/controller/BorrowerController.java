/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.modle.BorrowerDo;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.util.RequestCheckUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 借款人接口
 *
 * @author caogu.wyp
 */
@RestController
public class BorrowerController {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @ApiOperation(value = "借款人登录接口", notes = "输入手机号，短信验证码，图片验证码，验证成功即可登录")
    //@ApiImplicitParam(name = "borrowerLoginRequest", value = "borrowerLoginRequest", required = true, dataType = "BorrowerLoginRequest")
    @RequestMapping(value = "/borrowers/login", method = RequestMethod.POST)
    public BaseResult login(BorrowerLoginRequest borrowerLoginRequest, HttpSession httpSession) {

        System.out.println("login,sessionId" + httpSession.getId());

        BaseResult result = new BaseResult();

        try {
            RequestCheckUtil.checkBorrowerLoginRequest(borrowerLoginRequest);
            //校验图片验证码
            verifyImageCode(borrowerLoginRequest);
            //校验短信
            verifyOTP(borrowerLoginRequest);
            //更新用户信息
            updateBorrowerInfo(borrowerLoginRequest);

        } catch (Exception e) {

            e.printStackTrace();
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param borrowerLoginRequest
     */
    private void verifyImageCode(BorrowerLoginRequest borrowerLoginRequest)
                                                                           throws LoanPlatformException {
        String imageCode = SimpleCacheUtil.getImageCode(borrowerLoginRequest.getImageCodeToken());

        if (!StringUtils.endsWith(imageCode, borrowerLoginRequest.getImageCode())) {
            throw new LoanPlatformException("图片验证码验证失败！");
        }

        //验证通过，清空缓存
        SimpleCacheUtil.removeImageCode(borrowerLoginRequest.getImageCodeToken());
    }

    /**
     * 
     * @param borrowerLoginRequest
     */
    private void verifyOTP(BorrowerLoginRequest borrowerLoginRequest) throws LoanPlatformException {

        String SMSCode = SimpleCacheUtil.getSMSCode(borrowerLoginRequest.getPhoneNo());

        if (!StringUtils.endsWith(SMSCode, borrowerLoginRequest.getSMSCode())) {
            throw new LoanPlatformException("短信验证码验证失败！");
        }
        //验证通过，清空缓存
        SimpleCacheUtil.removeSMSCode(borrowerLoginRequest.getPhoneNo());
    }

    /**
     *
     * @param borrowerLoginRequest
     */
    private void updateBorrowerInfo(BorrowerLoginRequest borrowerLoginRequest) {
        /**
         * 将借款人的手机号存入数据库
         */
        BorrowerDo borrowerDo = new BorrowerDo();
        borrowerDo.setPhoneNo(borrowerLoginRequest.getPhoneNo());
        borrowerRepository.save(borrowerDo);

    }
}
