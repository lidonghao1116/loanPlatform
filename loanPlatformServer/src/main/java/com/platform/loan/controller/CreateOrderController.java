/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.dao.TopUpOrderRepository;
import com.platform.loan.pojo.request.CreateOrderRequest;
import com.platform.loan.pojo.result.CreateOrderResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.CreateOrderProcessor;
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
 * @version $Id: CreateOrderController.java, v 0.1 2018-05-27 上午1:20 caogu.wyp Exp $$
 */
@RestController
public class CreateOrderController {

    @Autowired
    private TopUpOrderRepository topUpOrderRepository;

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "创建业务订单，并唤起支付宝", notes = "创建业务订单，并唤起支付宝")
    @RequestMapping(value = "/api/pay/create", method = RequestMethod.POST)
    public CreateOrderResult createOrder(@RequestBody CreateOrderRequest createOrderRequest,
                                         HttpServletRequest httpServletRequest) {

        return LoanPlatformTemplate.run(new CreateOrderProcessor(), createOrderRequest,
            new CreateOrderResult(), httpServletRequest, topUpOrderRepository);
    }
}
