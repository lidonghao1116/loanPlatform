/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.*;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.*;
import com.platform.loan.pojo.request.QueryLoginManagerOrderRequest;
import com.platform.loan.pojo.result.QueryLoginManagerOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        GrabRecordRepository grabRecordRepository = (GrabRecordRepository) others[5];
        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        List<OrderDO> orders = queryOrders(queryLoginManagerOrderRequest, orderRepository,
            loginSession.getPhoneNo(), grabRecordRepository);

        List<LoanOrderViewModel> loanOrderViewModelList = getLoanOrderViewModels(
            borrowerRepository, providentFundRepository, socialSecurityRepository, orders);

        queryLoginManagerOrderResult.setViewList(loanOrderViewModelList);
    }

    private List<OrderDO> queryOrders(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                      OrderRepository orderRepository, String phoneNo,
                                      GrabRecordRepository grabRecordRepository) {

        if (StringUtils.isNotBlank(queryLoginManagerOrderRequest.getOrderId())) {

            //检查这笔订单，是否该用户抢过
            checkSelfGrabOrder(queryLoginManagerOrderRequest, phoneNo, grabRecordRepository);

            OrderDO orderDO = getOrderDO(queryLoginManagerOrderRequest, orderRepository);

            if (null == orderDO) {
                return Collections.EMPTY_LIST;
            }

            return Arrays.asList(orderDO);

        }

        /**
         * 1 先把自己所有的单子查出来
         * 2 根据过滤条件，把不符合的状态过滤掉
         * 3 剩下的去order库中查
         */

        List<GrabRecordDO> grabRecordDOs = grabRecordRepository.findGrabRecords(phoneNo);

        if (null == grabRecordDOs) {
            return Collections.emptyList();
        }
        //过滤掉不在查询条件中的记录
        filterByCondition(queryLoginManagerOrderRequest, grabRecordDOs);

        //从order库中查出
        Set<String> conditions = new HashSet<>();
        for (GrabRecordDO grabRecordDO : grabRecordDOs) {
            conditions.add(grabRecordDO.getOrderId());
        }

        return orderRepository.findOrders(conditions);

    }

    private void filterByCondition(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                   List<GrabRecordDO> grabRecordDOs) {
        Set<String> queryCondition = queryLoginManagerOrderRequest.getQueryCondition();
        if (null != queryCondition && queryCondition.size() > 0) {
            //需要过滤的状态
            Iterator<GrabRecordDO> iterator = grabRecordDOs.iterator();
            while (iterator.hasNext()) {
                GrabRecordDO grabRecordDO = iterator.next();
                if (!queryCondition.contains(grabRecordDO.getStatus())) {
                    iterator.remove();
                }
            }

        }
    }

    private OrderDO getOrderDO(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                               OrderRepository orderRepository) {
        OrderDO orderDO = orderRepository.findOrderDO(queryLoginManagerOrderRequest.getOrderId());

        return orderDO;
    }

    private void checkSelfGrabOrder(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                    String phoneNo, GrabRecordRepository grabRecordRepository) {
        GrabRecordDO grabRecordDO = grabRecordRepository.findGrabRecordDO(
            queryLoginManagerOrderRequest.getOrderId(), phoneNo);

        if (null == grabRecordDO) {
            throw new LoanPlatformException("不允许查询自己未抢过的订单，orderId："
                                            + queryLoginManagerOrderRequest.getOrderId());
        }
    }

    public static List<LoanOrderViewModel> getLoanOrderViewModels(BorrowerRepository borrowerRepository,
                                                                  ProvidentFundRepository providentFundRepository,
                                                                  SocialSecurityRepository socialSecurityRepository,
                                                                  List<OrderDO> orderDOList) {

        List<LoanOrderViewModel> loanOrderViewModelList = new ArrayList<>();

        if (null == orderDOList) {
            return loanOrderViewModelList;
        }

        for (OrderDO orderDO : orderDOList) {

            LoanOrderViewModel loanOrderViewModel = getLoanOrderViewModel(borrowerRepository,
                providentFundRepository, socialSecurityRepository, orderDO);

            loanOrderViewModelList.add(loanOrderViewModel);
        }

        return loanOrderViewModelList;
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
