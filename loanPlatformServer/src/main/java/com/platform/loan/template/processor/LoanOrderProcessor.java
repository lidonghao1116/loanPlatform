/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import com.platform.loan.template.Processor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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

        Iterable<OrderDO> borrowerOrderList = orderRepository.findAll();

        List<LoanOrderViewModel> list = new ArrayList<>();

        borrowerOrderList.forEach(borrowerOrderDO -> {

            LoanOrderViewModel model = new LoanOrderViewModel();
            model.setApplyTime(borrowerOrderDO.getCreateTime().toString());
            model.setMaskPhoneNo(maskBorrowerPhone(borrowerOrderDO));
            model.setPrice(borrowerOrderDO.getPrice().toString());
            model.setLoanType(borrowerOrderDO.getLoanType());
            model.setLoanLimit(borrowerOrderDO.getLoanLimit());
            model.setOrderStatus(borrowerOrderDO.getOrderStatus());
            //查人信息
            BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(borrowerOrderDO
                .getBorrowerPhoneNo());
            if (null != borrowerDO) {
                model.setMaskBorrowerName(maskBorrowerName(borrowerDO));
                model.setProfession(borrowerDO.getProfession());
                model.setMonthlyIncome(borrowerDO.getMonthlyIncome());
                model.setIncomeType(borrowerDO.getIncomeType());
                model.setHouseInfo(borrowerDO.getHouseInfo());
                model.setCarInfo(borrowerDO.getCarInfo());
            }
            list.add(model);
        });

        loanOrderResult.setPageNum(1);
        loanOrderResult.setViewList(list);

    }

    private String maskBorrowerPhone(OrderDO borrowerOrderDO) {
        //电话号码，取前三位，后面全取*
        if (StringUtils.isBlank(borrowerOrderDO.getBorrowerPhoneNo())
            || borrowerOrderDO.getBorrowerPhoneNo().length() < 4) {
            return borrowerOrderDO.getBorrowerPhoneNo();
        }
        return borrowerOrderDO.getBorrowerPhoneNo().substring(0, 3) + "********";
    }

    private String maskBorrowerName(BorrowerDO borrowerDO) {
        //姓名，取第一个字，后面2个*

        if (StringUtils.isBlank(borrowerDO.getName())) {
            return borrowerDO.getName();
        }

        String name = borrowerDO.getName();
        char first = name.charAt(0);

        return first + "**";
    }
}
