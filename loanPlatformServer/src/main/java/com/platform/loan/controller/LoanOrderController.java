package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.LoanOrderProcessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderController.java, v 0.1 2018-05-20 下午9:08 caogu.wyp Exp $$
 */
@RestController
public class LoanOrderController {

    @Autowired
    private OrderRepository    orderRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @ApiOperation(value = "查询可抢状态的订单", notes = "查询可抢状态的订单,此查询接口不需要做登陆校验")
    @RequestMapping(value = "/api/manager/orders", method = RequestMethod.GET)
    public LoanOrderResult queryInitLoanOrderList(LoanOrderRequest loanOrderRequest) {

        return LoanPlatformTemplate.run(new LoanOrderProcessor(), loanOrderRequest,
            new LoanOrderResult(), orderRepository, borrowerRepository);

    }

}
