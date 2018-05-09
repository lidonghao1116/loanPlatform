/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDo;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.BorrowerLoginResult;
import com.platform.loan.pojo.result.LoanTypeResult;
import com.platform.loan.util.RequestCheckUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/api/borrower/login", method = RequestMethod.POST)
    public BorrowerLoginResult login(BorrowerLoginRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        BorrowerLoginResult result = new BorrowerLoginResult();

        try {
            //请求参数判空
            RequestCheckUtil.checkBorrowerLoginRequest(request);
            //校验图片验证码
            verifyImageCode(request);
            //校验短信
            verifyOTP(request);
            //更新用户信息
            updateBorrowerInfo(request);
            //下发token
            initAccessToken(result, request);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }

    @ApiOperation(value = "获取借款类型列表", notes = "借款人登录后进入的借款类型列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @RequestMapping(value = "/api/borrower/loantypes", method = RequestMethod.GET)
    public LoanTypeResult queryLoanTypes(HttpServletRequest request) {

        LoanTypeResult loanTypeResult = new LoanTypeResult();

        List<String> loans = new ArrayList<String>();

        for (LoanTypeEnum loanTypeEnum : LoanTypeEnum.values()) {
            loans.add(loanTypeEnum.getLoanName());
        }

        loanTypeResult.setLoanTypes(loans);

        return loanTypeResult;
    }

    /**
     *
     * @param request
     */
    private void verifyImageCode(BorrowerLoginRequest request) throws LoanPlatformException {
        String imageCode = SimpleCacheUtil.getImageCode(request.getImageCodeToken());

        if (!StringUtils.endsWith(imageCode, request.getImageCode())) {
            throw new LoanPlatformException("图片验证码验证失败！");
        }

        //验证通过，清空缓存
        SimpleCacheUtil.removeImageCode(request.getImageCodeToken());
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

    private void initAccessToken(BorrowerLoginResult result, BorrowerLoginRequest request)
                                                                                          throws UnsupportedEncodingException {

        LoginSession loginSession = new LoginSession();
        loginSession.setPhoneNo(request.getPhoneNo());
        loginSession.setBiz("borrower");
        result.setAccessToken(JwtUtil.createJwt(loginSession));

    }

}
