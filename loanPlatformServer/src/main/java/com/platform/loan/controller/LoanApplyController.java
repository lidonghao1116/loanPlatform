package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.pojo.request.LoanApplyRequest;
import com.platform.loan.pojo.result.LoanApplyResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.LoanApplyProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanApplyController.java, v 0.1 2018-05-11 上午12:20 caogu.wyp Exp $$
 */
@RestController
public class LoanApplyController {

    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private OrderRepository    orderRepository;

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "信贷申请接口", notes = "微粒贷，公积金等贷款等方式的申请")
    @RequestMapping(value = "/api/borrower/loan/apply", method = RequestMethod.POST)
    public LoanApplyResult loanApply(@RequestBody LoanApplyRequest loanApplyRequest,
                                     HttpServletRequest httpServletRequest) {

        return LoanPlatformTemplate.run(new LoanApplyProcessor(), loanApplyRequest,
            new LoanApplyResult(), httpServletRequest, borrowerRepository, orderRepository);

    }

}
