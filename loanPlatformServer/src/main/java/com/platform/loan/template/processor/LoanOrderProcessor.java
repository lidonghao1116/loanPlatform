/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.BorrowerOrderStatusEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.modle.ProvidentFundDO;
import com.platform.loan.pojo.modle.SocialSecurityDO;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderProcessor.java, v 0.1 2018-05-21 下午11:56 caogu.wyp Exp $$
 */
public class LoanOrderProcessor implements Processor<LoanOrderRequest, LoanOrderResult> {
    @Override
    public void process(LoanOrderRequest loanOrderRequest, LoanOrderResult loanOrderResult,
                        Object... others) throws Exception {

        OrderRepository orderRepository = (OrderRepository) others[0];
        BorrowerRepository borrowerRepository = (BorrowerRepository) others[1];
        ProvidentFundRepository providentFundRepository = (ProvidentFundRepository) others[2];
        SocialSecurityRepository socialSecurityRepository = (SocialSecurityRepository) others[3];

        //如果穿了orderId，优先使用orderId查
        Iterable<OrderDO> orders = queryOrders(loanOrderRequest, orderRepository);

        List<LoanOrderViewModel> list = getLoanOrderViewModels(borrowerRepository,
            providentFundRepository, socialSecurityRepository, orders);

        loanOrderResult.setPageNum(1);
        loanOrderResult.setTotalPageNum(1);
        loanOrderResult.setViewList(list);

    }

    private Iterable<OrderDO> queryOrders(LoanOrderRequest loanOrderRequest,
                                          OrderRepository orderRepository) {

        if (StringUtils.isNotBlank(loanOrderRequest.getOrderId())) {

            OrderDO OrderDO = orderRepository.findOrderDO(loanOrderRequest.getOrderId());
            if (null == OrderDO) {
                return Collections.EMPTY_LIST;
            }
            return Arrays.asList(OrderDO);

        } else {
            Iterable<OrderDO> borrowerOrderList = orderRepository
                .findOrderDOSByOrderStatusOrderByCreateTimeDesc(BorrowerOrderStatusEnum.ENABLE_GRAB
                    .getStatus());

            return borrowerOrderList;
        }

    }

    public static List<LoanOrderViewModel> getLoanOrderViewModels(BorrowerRepository borrowerRepository,
                                                                  ProvidentFundRepository providentFundRepository,
                                                                  SocialSecurityRepository socialSecurityRepository,
                                                                  Iterable<OrderDO> borrowerOrderList) {
        List<LoanOrderViewModel> list = new ArrayList<>();

        borrowerOrderList.forEach(orderDO -> {

            //查询订单信息
            LoanOrderViewModel model = LoanUtil.getLoanOrderViewModel(orderDO, false);
            //查询借款人信息
            BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(orderDO
                .getBorrowerPhoneNo());
            LoanUtil.initBorrowerInfo(model, borrowerDO, false);

            //查询公积金信息
            ProvidentFundDO providentFundDO = providentFundRepository
                .findProvidentFundDoByPhoneNo(orderDO.getBorrowerPhoneNo());
            LoanUtil.initFundInfo(model, providentFundDO);
            //查询社保信息
            SocialSecurityDO socialSecurityDO = socialSecurityRepository
                .findSocialSecurityDoByPhoneNo(orderDO.getBorrowerPhoneNo());
            LoanUtil.initSocialInfo(model, socialSecurityDO);

            //拼描述信息
            LoanUtil.intBorrowerInfoDesc(model, borrowerDO);

            //根据不同类型订单，拼接首页数据
            LoanUtil.initNotLoginIndexDesc(model, borrowerDO, orderDO);

            list.add(model);
        });

        return list;
    }
}
