package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import com.platform.loan.pojo.request.QueryLoginManagerOrderRequest;
import com.platform.loan.pojo.result.QueryLoginManagerOrderResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.LoanOrderProcessor;
import com.platform.loan.template.processor.QueryLoginManagerOrderProcessor;
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
 * @version $Id: LoanOrderController.java, v 0.1 2018-05-20 下午9:08 caogu.wyp Exp $$
 */
@RestController
public class LoanOrderController {

    @Autowired
    private OrderRepository          orderRepository;

    @Autowired
    private BorrowerRepository       borrowerRepository;

    @Autowired
    private ProvidentFundRepository  providentFundRepository;

    @Autowired
    private SocialSecurityRepository socialSecurityRepository;

    @ApiOperation(value = "查询可抢状态的订单", notes = "查询可抢状态的订单,此查询接口不需要做登陆校验,queryCondition为查询条件")
    @RequestMapping(value = "/api/manager/orders", method = RequestMethod.POST)
    public LoanOrderResult queryInitLoanOrderList(@RequestBody LoanOrderRequest loanOrderRequest) {

        return LoanPlatformTemplate.run(new LoanOrderProcessor(), loanOrderRequest,
            new LoanOrderResult(), orderRepository, borrowerRepository, providentFundRepository,
            socialSecurityRepository);

    }

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "查询当前登录信贷经理的订单", notes = "查询当前信贷经理的订单,入参是订单的状态,未填写则查询所有的状态,参考BorrowerOrderStatusEnum")
    @RequestMapping(value = "/api/manager/login/orders", method = RequestMethod.GET)
    public QueryLoginManagerOrderResult queryLoginManagerOrder(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                                               HttpServletRequest httpServletRequest) {

        return LoanPlatformTemplate.run(new QueryLoginManagerOrderProcessor(),
            queryLoginManagerOrderRequest, new QueryLoginManagerOrderResult(), httpServletRequest,
            orderRepository, borrowerRepository, providentFundRepository, socialSecurityRepository);
    }

}
