/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.pojo.request.VerifyIdNoRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.VerifyIdNoProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: VerifyIdNoController.java, v 0.1 2018-05-27 下午1:53 caogu.wyp Exp $$
 */
@RestController
public class VerifyIdNoController {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private ManagerRepository  managerRepository;

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "身份认证", notes = "身份认证信息")
    @RequestMapping(value = "/api/idNo/verify", method = RequestMethod.GET)
    public BaseResult verifyIdNo(VerifyIdNoRequest request, HttpServletRequest httpServletRequest) {
        return LoanPlatformTemplate.run(new VerifyIdNoProcessor(), request, new BaseResult(),
            httpServletRequest, borrowerRepository, managerRepository);
    }
}
