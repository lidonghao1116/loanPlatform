/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.request.VerifyIdNoRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.pojo.result.BorrowerInfoResult;
import com.platform.loan.pojo.result.LoanTypeResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.QueryBorrowerInfoByAccessTokenProcessor;
import com.platform.loan.template.processor.QueryLoanTypesProcessor;
import com.platform.loan.template.processor.VerifyIdNoProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 借款人接口
 *
 * @author caogu.wyp
 */
@RestController
public class BorrowerController {

    @Autowired
    private BorrowerRepository       borrowerRepository;

    @Autowired
    private ProvidentFundRepository  providentFundRepository;

    @Autowired
    private SocialSecurityRepository socialSecurityRepository;

    @ApiOperation(value = "获取借款类型列表", notes = "借款人登录后进入的借款类型列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @RequestMapping(value = "/api/borrower/loan/types", method = RequestMethod.GET)
    public LoanTypeResult queryLoanTypes(BaseRequest request) {

        return LoanPlatformTemplate.run(new QueryLoanTypesProcessor(), request,
            new LoanTypeResult());
    }

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "获取借款人身份证信息", notes = "通过jwt token获取当前登录借款人身份证信息")
    @RequestMapping(value = "/api/borrower/certificate", method = RequestMethod.GET)
    public BorrowerInfoResult queryBorrowerInfoByAccessToken(BaseRequest baseRequest,
                                                             HttpServletRequest request) {

        return LoanPlatformTemplate.run(new QueryBorrowerInfoByAccessTokenProcessor(), baseRequest,
            new BorrowerInfoResult(), request, borrowerRepository, providentFundRepository,
            socialSecurityRepository);

    }

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "身份认证", notes = "身份认证信息")
    @RequestMapping(value = "/api/borrower/idNo/verify", method = RequestMethod.GET)
    public BaseResult verifyIdNo(VerifyIdNoRequest request, HttpServletRequest httpServletRequest) {
        return LoanPlatformTemplate.run(new VerifyIdNoProcessor(), request, new BaseResult(),
            httpServletRequest, borrowerRepository);
    }

}
