/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.pojo.request.GrabLoanOrderRequest;
import com.platform.loan.pojo.result.GrabLoanOrderResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.GrabLoanOrderProcessor;
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
 * @version $Id: CreditManagerController.java, v 0.1 2018-05-20 下午6:29 caogu.wyp Exp $$
 */
@RestController
public class ManagerController {

    @Autowired
    private OrderRepository   orderRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "抢订单", notes = "抢订单")
    @RequestMapping(value = "/api/manager/order/grab", method = RequestMethod.POST)
    public GrabLoanOrderResult grabLoanOrder(@RequestBody GrabLoanOrderRequest grabLoanOrderRequest,
                                             HttpServletRequest httpServletRequest) {

        return LoanPlatformTemplate.run(new GrabLoanOrderProcessor(), grabLoanOrderRequest,
            new GrabLoanOrderResult(), httpServletRequest, orderRepository, managerRepository);
    }
}
