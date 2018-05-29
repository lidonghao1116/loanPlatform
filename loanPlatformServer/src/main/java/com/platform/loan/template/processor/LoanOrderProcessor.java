/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.CommonConstants;
import com.platform.loan.constant.LoanOrderQueryConditionEnum;
import com.platform.loan.dao.*;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.*;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanLogUtil;
import com.platform.loan.util.LoanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
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
        GrabRecordRepository grabRecordRepository = (GrabRecordRepository) others[4];
        HttpServletRequest httpServletRequest = (HttpServletRequest) others[5];

        //如果穿了orderId，优先使用orderId查
        List<OrderDO> orders = queryOrders(loanOrderRequest, orderRepository);

        //过滤掉自己抢过的订单
        filterOrders(orders, httpServletRequest, grabRecordRepository);

        List<LoanOrderViewModel> list = getLoanOrderViewModels(borrowerRepository,
            providentFundRepository, socialSecurityRepository, orders);

        filterLoanOrderViewModel(list, loanOrderRequest);

        loanOrderResult.setPageNum(1);
        loanOrderResult.setTotalPageNum(1);
        loanOrderResult.setViewList(list);

    }

    private void filterOrders(List<OrderDO> orders, HttpServletRequest httpServletRequest,
                              GrabRecordRepository grabRecordRepository) {

        if (StringUtils.isBlank(httpServletRequest
            .getHeader(CommonConstants.AUTHORIZATION_HEARDER_KEY))) {
            return;
        }

        LoginSession loginSession = null;
        try {

            loginSession = JwtUtil.getLoginSession(httpServletRequest);
        } catch (Exception e) {
            //这里失败，不要中断
            LoanLogUtil.getLogger(LoanOrderProcessor.class).warn("请求查询匿名订单列表时，token验证失败");
        }

        List<GrabRecordDO> grabRecordDOS = grabRecordRepository.findGrabRecords(loginSession
            .getPhoneNo());

        if (null == grabRecordDOS || grabRecordDOS.size() == 0) {
            return;
        }

        Set<String> grabOrderIds = new HashSet<>();
        for (GrabRecordDO grabRecordDO : grabRecordDOS) {
            grabOrderIds.add(grabRecordDO.getOrderId());
        }

        Iterator<OrderDO> iterator = orders.iterator();
        while (iterator.hasNext()) {
            OrderDO orderDO = iterator.next();

            if (grabOrderIds.contains(orderDO.getOrderId())) {
                iterator.remove();
            }

        }

    }

    private void filterLoanOrderViewModel(List<LoanOrderViewModel> loanOrderViewModels,
                                          LoanOrderRequest loanOrderRequest) {
        if (StringUtils.isNotBlank(loanOrderRequest.getOrderId())) {
            return;
        }

        filterCity(loanOrderRequest.getCity(), loanOrderViewModels);

        filterQueryConditions(loanOrderViewModels, loanOrderRequest.getQueryCondition());
    }

    private void filterCity(String city, List<LoanOrderViewModel> loanOrderViewModels) {

        Iterator<LoanOrderViewModel> iterator = loanOrderViewModels.iterator();
        if (StringUtils.isNotBlank(city) && !"不限城市".equals(city)) {
            while (iterator.hasNext()) {
                LoanOrderViewModel loanOrderViewModel = iterator.next();
                if (!city.equals(loanOrderViewModel.getLoanCity())) {
                    iterator.remove();
                }

            }
        }

    }

    /**
     *
     * 先给订单打标 Set A
     * queryCondition Set B
     *
     * A ＋B size和 ＝ m
     *  A B 取交集后，size ＝ n
     *
     *  如果m=n ，则说明不符合条件，删除
     *  如果 n<m ,则说明符合条件，加入
     *
     */
    private void filterQueryConditions(List<LoanOrderViewModel> loanOrderViewModels,
                                       Set<String> queryConditions) {

        if (null == queryConditions || 0 == queryConditions.size()) {
            return;
        }

        Iterator<LoanOrderViewModel> iterator = loanOrderViewModels.iterator();

        while (iterator.hasNext()) {
            LoanOrderViewModel loanOrderViewModel = iterator.next();

            Set<String> ordersMark = getMarks(loanOrderViewModel);

            int totalSize = ordersMark.size() + queryConditions.size();
            if (ordersMark.addAll(queryConditions)) {
                if (totalSize == ordersMark.size()) {
                    iterator.remove();
                }
            }

        }
    }

    private Set<String> getMarks(LoanOrderViewModel loanOrderViewModel) {
        Set<String> marks = new HashSet<String>();
        if (StringUtils.isNotBlank(loanOrderViewModel.getCarInfo())
            && !"无车".equals(loanOrderViewModel.getCarInfo())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_CAR.getCode());
        }
        if (StringUtils.isNotBlank(loanOrderViewModel.getHouseInfo())
            && !"无房产".equals(loanOrderViewModel.getHouseInfo())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_HOUSE.getCode());
        }
        if (StringUtils.isNotBlank(loanOrderViewModel.getProvidentFundTaskId())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_FUND.getCode());

        }
        if (StringUtils.isNotBlank(loanOrderViewModel.getSocialSecurityTaskId())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_SOCIAL.getCode());

        }

        if (StringUtils.isNotBlank(loanOrderViewModel.getProfession())
            && "事业单位".equals(loanOrderViewModel.getProfession())) {
            marks.add(LoanOrderQueryConditionEnum.SERVANT.getCode());

        }
        if (StringUtils.isNotBlank(loanOrderViewModel.getPersonalnsurance())
            && !"无".equals(loanOrderViewModel.getProfession())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_POLICY.getCode());

        }

        if (StringUtils.isNotBlank(loanOrderViewModel.getCreditLimit())
            && !"无".equals(loanOrderViewModel.getProfession())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_CREDIT_CARD.getCode());

        }
        if (StringUtils.isNotBlank(loanOrderViewModel.getWeiLiDaiKeJie())) {
            marks.add(LoanOrderQueryConditionEnum.HAVE_WEI_LI_DAI.getCode());
        }

        return marks;

    }

    private List<OrderDO> queryOrders(LoanOrderRequest loanOrderRequest,
                                      OrderRepository orderRepository) {

        if (StringUtils.isNotBlank(loanOrderRequest.getOrderId())) {

            OrderDO orderDO = orderRepository.findOrderDO(loanOrderRequest.getOrderId());
            if (null == orderDO) {
                return Collections.EMPTY_LIST;
            }
            return Arrays.asList(orderDO);

        } else {

            return orderRepository.findCanGrabOrders();
        }

    }

    public static List<LoanOrderViewModel> getLoanOrderViewModels(BorrowerRepository borrowerRepository,
                                                                  ProvidentFundRepository providentFundRepository,
                                                                  SocialSecurityRepository socialSecurityRepository,
                                                                  Iterable<OrderDO> orderDOS) {
        List<LoanOrderViewModel> list = new ArrayList<>();

        for (OrderDO orderDO : orderDOS) {
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
        }

        return list;
    }
}
