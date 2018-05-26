/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.dao.ProvidentFundRepository;
import com.platform.loan.dao.SocialSecurityRepository;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
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
        List<OrderDO> orderDOList = orderRepository.findOrderDOSByManagerPhoneNo(loginSession
            .getPhoneNo());

        if (null == orderDOList || orderDOList.size() == 0) {
            return;
        }

        List<LoanOrderViewModel> loanOrderViewModelList = getLoanOrderViewModels(
            queryLoginManagerOrderRequest, borrowerRepository, providentFundRepository,
            socialSecurityRepository, orderDOList);

        queryLoginManagerOrderResult.setViewList(loanOrderViewModelList);
    }

    private List<LoanOrderViewModel> getLoanOrderViewModels(QueryLoginManagerOrderRequest queryLoginManagerOrderRequest,
                                                            BorrowerRepository borrowerRepository,
                                                            ProvidentFundRepository providentFundRepository,
                                                            SocialSecurityRepository socialSecurityRepository,
                                                            List<OrderDO> orderDOList) {
        //根据条件过滤
        List<LoanOrderViewModel> loanOrderViewModelList = new ArrayList<>();

        orderDOList.forEach(borrowerOrderDO -> {

            if (queryLoginManagerOrderRequest.getQueryCondition().contains(
                borrowerOrderDO.getOrderStatus())) {

                LoanOrderViewModel loanOrderViewModel = LoanUtil.getLoanOrderViewModel(
                    borrowerOrderDO, true);
                //查人信息
            BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(borrowerOrderDO
                .getBorrowerPhoneNo());

            LoanUtil.initBorrowerInfo(loanOrderViewModel, borrowerDO, true);

            //查询公积金信息
            ProvidentFundDO providentFundDO = providentFundRepository
                .findProvidentFundDoByPhoneNo(borrowerOrderDO.getBorrowerPhoneNo());
            LoanUtil.initFundInfo(loanOrderViewModel, providentFundDO);
            //查询社保信息
            SocialSecurityDO socialSecurityDO = socialSecurityRepository
                .findSocialSecurityDoByPhoneNo(borrowerOrderDO.getBorrowerPhoneNo());
            LoanUtil.initSocialInfo(loanOrderViewModel, socialSecurityDO);

            //拼描述信息
            LoanUtil.intBorrowerInfo(loanOrderViewModel, borrowerDO);

            loanOrderViewModelList.add(loanOrderViewModel);
        }
    })  ;
        return loanOrderViewModelList;
    }

}
