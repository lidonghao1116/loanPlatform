/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.modle.ProvidentFundDO;
import com.platform.loan.pojo.modle.SocialSecurityDO;
import com.platform.loan.pojo.request.QueryLoginManagerOrderRequest;
import com.platform.loan.pojo.result.QueryLoginManagerOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author caogu.wyp
 * @version $Id: QueryLoginManagerOrderProcessor.java, v 0.1 2018-05-24 上午1:04 caogu.wyp Exp $$
 */
public class QueryLoginManagerOrderProcessor
                                            implements
                                            Processor<QueryLoginManagerOrderRequest, QueryLoginManagerOrderResult> {
    @Override
    public void process(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                        QueryLoginManagerOrderResult queryLoginManagerOrderResult, Object... others)
                                                                                                    throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        OrderRepository orderRepository = (OrderRepository) others[1];
        BorrowerRepository borrowerRepository = (BorrowerRepository) others[2];
        ProvidentFundRepository providentFundRepository = (ProvidentFundRepository) others[3];
        SocialSecurityRepository socialSecurityRepository = (SocialSecurityRepository) others[4];
        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        List<OrderDO> orders = queryOrders(queryLoginManagerOrderRequest, orderRepository,
            loginSession.getPhoneNo());

        List<LoanOrderViewModel> loanOrderViewModelList = getLoanOrderViewModels(
            queryLoginManagerOrderRequest, borrowerRepository, providentFundRepository,
            socialSecurityRepository, orders);

        queryLoginManagerOrderResult.setViewList(loanOrderViewModelList);
    }

    private List<OrderDO> queryOrders(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                      OrderRepository orderRepository, String phoneNo) {

        if (StringUtils.isNotBlank(queryLoginManagerOrderRequest.getOrderId())) {

            OrderDO orderDO = orderRepository.findOrderDO(queryLoginManagerOrderRequest
                .getOrderId());
            if (null == orderDO) {
                return Collections.EMPTY_LIST;
            }
            if (!phoneNo.equals(orderDO.getManagerPhoneNo())) {
                throw new LoanPlatformException("不允许查非自己的订单详情");
            }

            return Arrays.asList(orderDO);

        } else {
            return orderRepository.findOrderDOSByManagerPhoneNo(phoneNo);
        }

    }

    public static List<LoanOrderViewModel> getLoanOrderViewModels(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                                                  BorrowerRepository borrowerRepository,
                                                                  ProvidentFundRepository providentFundRepository,
                                                                  SocialSecurityRepository socialSecurityRepository,
                                                                  List<OrderDO> orderDOList) {

        //根据条件过滤
        List<LoanOrderViewModel> loanOrderViewModelList = new ArrayList<>();

        if (null == orderDOList) {
            return loanOrderViewModelList;
        }

        if (haveQueryCondition(queryLoginManagerOrderRequest)) {

            for (OrderDO orderDO : orderDOList) {

                if (queryLoginManagerOrderRequest.getQueryCondition().contains(
                    orderDO.getOrderStatus())) {

                    LoanOrderViewModel loanOrderViewModel = getLoanOrderViewModel(
                        borrowerRepository, providentFundRepository, socialSecurityRepository,
                        orderDO);
                    loanOrderViewModelList.add(loanOrderViewModel);
                }
            }

        } else {

            for (OrderDO orderDO : orderDOList) {

                LoanOrderViewModel loanOrderViewModel = getLoanOrderViewModel(borrowerRepository,
                    providentFundRepository, socialSecurityRepository, orderDO);

                loanOrderViewModelList.add(loanOrderViewModel);
            }

        }

        return loanOrderViewModelList;
    }

    private static boolean haveQueryCondition(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest) {
        // 判断是否有查询条件
        return null != queryLoginManagerOrderRequest.getQueryCondition()
               && queryLoginManagerOrderRequest.getQueryCondition().size() > 0;
    }

    private static LoanOrderViewModel getLoanOrderViewModel(BorrowerRepository borrowerRepository,
                                                            ProvidentFundRepository providentFundRepository,
                                                            SocialSecurityRepository socialSecurityRepository,
                                                            OrderDO orderDO) {
        LoanOrderViewModel loanOrderViewModel = LoanUtil.getLoanOrderViewModel(orderDO, true);
        //查人信息
        BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(orderDO
            .getBorrowerPhoneNo());

        LoanUtil.initBorrowerInfo(loanOrderViewModel, borrowerDO, true);

        //查询公积金信息
        ProvidentFundDO providentFundDO = providentFundRepository
            .findProvidentFundDoByPhoneNo(orderDO.getBorrowerPhoneNo());
        LoanUtil.initFundInfo(loanOrderViewModel, providentFundDO);
        //查询社保信息
        SocialSecurityDO socialSecurityDO = socialSecurityRepository
            .findSocialSecurityDoByPhoneNo(orderDO.getBorrowerPhoneNo());
        LoanUtil.initSocialInfo(loanOrderViewModel, socialSecurityDO);

        //拼描述信息
        LoanUtil.intBorrowerInfoDesc(loanOrderViewModel, borrowerDO);
        return loanOrderViewModel;
    }

}
