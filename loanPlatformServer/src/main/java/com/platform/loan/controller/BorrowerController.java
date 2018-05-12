/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.platform.loan.constant.CommonConstants;
import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDo;
import com.platform.loan.pojo.result.BorrowerInfoResult;
import com.platform.loan.pojo.result.LoanTypeResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation(value = "获取借款类型列表", notes = "借款人登录后进入的借款类型列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @RequestMapping(value = "/api/borrower/loan/types", method = RequestMethod.GET)
    public LoanTypeResult queryLoanTypes() {

        LoanTypeResult loanTypeResult = new LoanTypeResult();

        List<String> loans = new ArrayList<String>();

        for (LoanTypeEnum loanTypeEnum : LoanTypeEnum.values()) {
            loans.add(loanTypeEnum.getLoanName());
        }

        loanTypeResult.setLoanTypes(loans);
        return loanTypeResult;
    }

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "获取借款人身份证信息", notes = "通过jwt token获取当前登录借款人身份证信息")
    @RequestMapping(value = "/api/borrower/certificate", method = RequestMethod.GET)
    public BorrowerInfoResult getBorrowerInfoByAccessToken(HttpServletRequest request) {

        String jwtToken = request.getHeader(CommonConstants.AUTHORIZATION_HEARDER_KEY);

        BorrowerInfoResult result = new BorrowerInfoResult();
        try {

            DecodedJWT jwt = JwtUtil.verifyJwt(jwtToken);

            initResult(jwt, result);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }

    private void initResult(DecodedJWT jwt, BorrowerInfoResult result) {

        LoginSession ls = JSONObject.parseObject(jwt.getClaim(CommonConstants.CLAIM_LOGININFO_KEY)
            .asString(), LoginSession.class);

        BorrowerDo borrowerDo = borrowerRepository.findBorrowerDoByPhoneNo(ls.getPhoneNo());

        if (null == borrowerDo) {
            return;
        }
        result.setPhoneNo(borrowerDo.getPhoneNo());
        result.setIdNo(borrowerDo.getIdNo());
        result.setName(borrowerDo.getName());
    }

}
