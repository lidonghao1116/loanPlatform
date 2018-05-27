/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.pojo.request.CreateOrderRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.CreateOrderProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author caogu.wyp
 * @version $Id: CreateOrderController.java, v 0.1 2018-05-27 上午1:20 caogu.wyp Exp $$
 */
@Controller
public class CreateOrderController {

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "创建业务订单，并唤起支付宝", notes = "创建业务订单，并唤起支付宝")
    @RequestMapping(value = "/api/pay/create", method = RequestMethod.GET)
    public void createOrder(CreateOrderRequest createOrderRequest, HttpServletResponse response) {

        LoanPlatformTemplate.run(new CreateOrderProcessor(), createOrderRequest, new BaseResult(),
            response);

    }
}
