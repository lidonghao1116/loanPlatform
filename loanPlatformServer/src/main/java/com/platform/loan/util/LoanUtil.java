/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.loan.constant.BorrowerOrderStatusEnum;
import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.modle.ProvidentFundDO;
import com.platform.loan.pojo.modle.SocialSecurityDO;
import com.platform.loan.pojo.request.LoanApplyRequest;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author caogu.wyp
 * @version $Id: LoanPriceUtil.java, v 0.1 2018-05-18 上午1:54 caogu.wyp Exp $$
 */
public class LoanUtil {

    public static BigDecimal getPrice(LoanApplyRequest request) {
        //TODO 这里会有定价规则
        return new BigDecimal(13.14);
    }

    private static String processCity(String citys) {
        String city = null;
        try {
            String[] cityArray = StringUtils.split(citys.trim(), " ");

            city = cityArray[cityArray.length - 1];
        } catch (Exception e) {
            LoanLogUtil.getLogger(LoanUtil.class).warn("processCity error, citys : " + citys);
        }
        return city;
    }

    public static LoanOrderViewModel getLoanOrderViewModel(OrderDO orderDO, boolean isLogin) {

        LoanOrderViewModel model = new LoanOrderViewModel();
        model.setBorrowerPhoneNo(maskBorrowerPhone(orderDO));
        model.setApplyTime(orderDO.getCreateTime().toString());
        model.setPrice(orderDO.getPrice().toString());
        model.setLoanTypeDesc(LoanTypeEnum.getDescByName(orderDO.getLoanType()));
        model.setLoanLimit(orderDO.getLoanLimit());
        model.setOrderStatus(orderDO.getOrderStatus());
        model.setLoanCity(processCity(orderDO.getLoanCity()));
        model.setOrderId(orderDO.getOrderId());
        model.setLoanDeadline(orderDO.getLoanDeadline());
        model.setLoanPurpose(orderDO.getLoanPurpose());

        if (isLogin) {

            model.setBorrowerPhoneNo(orderDO.getBorrowerPhoneNo());
            model.setProcessResult(orderDO.getProcessResult());
            if (BorrowerOrderStatusEnum.GRAB_FINISH.getStatus().equals(orderDO.getOrderStatus())) {
                model.setGrabTime(orderDO.getGrabTime().toString());

            }

        }

        return model;
    }

    public static void initBorrowerInfo(LoanOrderViewModel model, BorrowerDO borrowerDO,
                                        boolean isLogin) {
        if (null == borrowerDO) {
            return;
        }

        model.setMaskBorrowerName(maskBorrowerName(borrowerDO));
        model.setProfession(borrowerDO.getProfession());
        model.setMonthlyIncome(borrowerDO.getMonthlyIncome());
        model.setIncomeType(borrowerDO.getIncomeType());
        model.setHouseInfo(borrowerDO.getHouseInfo());
        model.setCarInfo(borrowerDO.getCarInfo());
        model.setWeiLiDaiKeJie(borrowerDO.getWeiLiDaiKeJie());
        model.setWeiLiDaiTotal(borrowerDO.getWeiLiDaiTotal());

        if (isLogin) {
            model.setBorrowerName(borrowerDO.getName());
            model.setBorrowerIdNo(borrowerDO.getIdNo());
        }

    }

    public static void intBorrowerInfoDesc(LoanOrderViewModel model, BorrowerDO borrowerDO) {

        /**
         * 职业＋收入＋房产＋社保
         */
        StringBuilder sb = new StringBuilder();
        sb.append(borrowerDO.getProfession()).append(" ");
        sb.append("月收入" + borrowerDO.getMonthlyIncome() + "元").append(" ");
        if (StringUtils.isNotBlank(model.getProvidentFundTaskId())) {
            sb.append("有公积金").append(" ");
        }

        if (StringUtils.isNotBlank(model.getSocialSecurityTaskId())) {
            sb.append("有社保").append(" ");
        }

        model.setBorrowerInfoDesc(sb.toString());

    }

    public static void initFundInfo(LoanOrderViewModel model, ProvidentFundDO providentFundDO) {

        if (null == providentFundDO) {
            return;
        }
        JSONObject object = JSONArray.parseObject(providentFundDO.getFundDetail());
        if (null == object) {
            LoanLogUtil.getLogger(LoanUtil.class).warn(
                "getFundDetail 转化成json失败:" + providentFundDO.getFundDetail());
            return;
        }
        model.setProvidentFundTaskId(object.getString("task_id"));
    }

    public static void initSocialInfo(LoanOrderViewModel model, SocialSecurityDO socialSecurityDO) {
        if (null == socialSecurityDO) {
            return;
        }
        JSONObject object = JSONArray.parseObject(socialSecurityDO.getSocialDetail());
        if (null == object) {
            LoanLogUtil.getLogger(LoanUtil.class).warn(
                "getSocialDetail 转化成json失败:" + socialSecurityDO.getSocialDetail());
            return;
        }
        model.setSocialSecurityTaskId(object.getString("task_id"));
    }

    private static String maskBorrowerPhone(OrderDO borrowerOrderDO) {
        //电话号码，取前三位，后面全取*
        if (StringUtils.isBlank(borrowerOrderDO.getBorrowerPhoneNo())
            || borrowerOrderDO.getBorrowerPhoneNo().length() < 4) {
            return borrowerOrderDO.getBorrowerPhoneNo();
        }
        return borrowerOrderDO.getBorrowerPhoneNo().substring(0, 3) + "********";
    }

    private static String maskBorrowerName(BorrowerDO borrowerDO) {
        //姓名，取第一个字，后面2个*

        if (StringUtils.isBlank(borrowerDO.getName())) {
            return borrowerDO.getName();
        }

        String name = borrowerDO.getName();
        char first = name.charAt(0);

        return first + "**";
    }

}
