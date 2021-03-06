/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.dao.SysConfigRepository;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.modle.*;
import com.platform.loan.pojo.request.LoanApplyRequest;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author caogu.wyp
 * @version $Id: LoanPriceUtil.java, v 0.1 2018-05-18 上午1:54 caogu.wyp Exp $$
 */
public class LoanUtil {

    public static BigDecimal getPrice(LoanApplyRequest request,
                                      SysConfigRepository sysConfigRepository) {

        SysConfigurationDO sysConfigurationDO = sysConfigRepository
            .findSysConfigurationDOByConfigKey(request.getLoanType());

        try {
            if (null != sysConfigurationDO
                && StringUtils.isNotBlank(sysConfigurationDO.getConfigValue())) {
                return new BigDecimal(sysConfigurationDO.getConfigValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BigDecimal(15);
        }
        return new BigDecimal(15);
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

        if (null == orderDO) {
            return model;
        }
        model.setBorrowerPhoneNo(maskBorrowerPhone(orderDO));
        model.setApplyTime(processApplyTime(orderDO));
        model.setPrice(orderDO.getPrice().toString());
        model.setLoanTypeDesc(LoanTypeEnum.getDescByName(orderDO.getLoanType()));
        model.setLoanLimit(orderDO.getLoanLimit());
        model.setLoanCity(processCity(orderDO.getLoanCity()));
        model.setOrderId(orderDO.getOrderId());
        model.setLoanDeadline(orderDO.getLoanDeadline());
        model.setLoanPurpose(orderDO.getLoanPurpose());

        if (isLogin) {
            model.setBorrowerPhoneNo(orderDO.getBorrowerPhoneNo());
            // model.setProcessResult(orderDO.getProcessResult());
            //TODO 这里
            model.setGrabTime(processApplyTime(orderDO));
        }

        return model;
    }

    private static String processApplyTime(OrderDO orderDO) {

        return orderDO.getCreateTime().toString().split(" ")[0];
    }

    public static void initBorrowerInfo(LoanOrderViewModel model, BorrowerDO borrowerDO,
                                        boolean isLogin) {
        if (null == borrowerDO) {
            return;
        }
        model.setCreditLimit(borrowerDO.getCreditLimit());
        model.setPersonalnsurance(borrowerDO.getPersonalnsurance());
        model.setMaskBorrowerName(maskBorrowerName(borrowerDO));
        model.setProfession(borrowerDO.getProfession());
        model.setMonthlyIncome(borrowerDO.getMonthlyIncome());
        model.setIncomeType(borrowerDO.getIncomeType());
        model.setHouseInfo(borrowerDO.getHouseInfo());
        model.setCarInfo(borrowerDO.getCarInfo());
        model.setWeiLiDaiKeJie(borrowerDO.getWeiLiDaiKeJie());
        model.setWeiLiDaiTotal(borrowerDO.getWeiLiDaiTotal());
        model.setMaskBorrowerIdNo(markBorrowerIdNo(borrowerDO));
        model.setCreditRecord(borrowerDO.getCreditRecord());
        model.setInsuranceCompany(borrowerDO.getInsuranceCompany());
        model.setInsuranceCoverage(borrowerDO.getInsuranceCoverage());
        model.setEduLevel(borrowerDO.getEduLevel());

        if (isLogin) {
            model.setBorrowerName(borrowerDO.getName());
            model.setBorrowerIdNo(borrowerDO.getIdNo());
        }

    }

    private static String markBorrowerIdNo(BorrowerDO borrowerDO) {

        if (StringUtils.isBlank(borrowerDO.getIdNo())) {
            return borrowerDO.getIdNo();
        }
        String idNo = borrowerDO.getIdNo();

        return idNo.substring(0, 4) + "**************";

    }

    public static void intBorrowerInfoDesc(LoanOrderViewModel model, BorrowerDO borrowerDO) {
        if (null == borrowerDO) {
            return;
        }
        /**
         * 职业＋收入＋房产＋社保
         */
        String profession = borrowerDO.getProfession();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(profession)) {
            sb.append(profession).append(" ");
        }
        String monthlyIncome = borrowerDO.getMonthlyIncome();
        if (StringUtils.isNotBlank(monthlyIncome)) {
            sb.append("月收入" + monthlyIncome + "元").append(" ");
        }
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
        model.setProvidentFundCity(object.getString("city"));
        if (LoanTypeEnum.PROVIDENT_FUND.getDesc().equals(model.getLoanTypeDesc())) {

            model.setMessageId(providentFundDO.getFundMessageId());
        }
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
        model.setSocialSecurityCity(object.getString("city"));

        if (LoanTypeEnum.SOCIAL_SECURITY.getDesc().equals(model.getLoanTypeDesc())) {
            model.setMessageId(socialSecurityDO.getSocialMessageId());
        }
    }

    private static String maskBorrowerPhone(OrderDO orderDO) {
        //电话号码，取前三位，后面全取*
        if (StringUtils.isBlank(orderDO.getBorrowerPhoneNo())
            || orderDO.getBorrowerPhoneNo().length() < 4) {
            return orderDO.getBorrowerPhoneNo();
        }
        return orderDO.getBorrowerPhoneNo().substring(0, 3) + "********";
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

    public static void initNotLoginIndexDesc(LoanOrderViewModel model, BorrowerDO borrowerDO,
                                             OrderDO orderDO) {

        StringBuilder sb = new StringBuilder();

        if (LoanTypeEnum.WEI_LI_DAI.getLoanName().equals(orderDO.getLoanType())) {

            sb.append("微粒贷总额");
            sb.append(borrowerDO.getWeiLiDaiTotal());
            sb.append("元 ");
            sb.append("微粒贷可借额度");
            sb.append(borrowerDO.getWeiLiDaiKeJie());
            sb.append("元");

        } else if (LoanTypeEnum.SOCIAL_SECURITY.getLoanName().equals(orderDO.getLoanType())) {
            sb.append("有社保");
        } else if (LoanTypeEnum.PROVIDENT_FUND.getLoanName().equals(orderDO.getLoanType())) {
            sb.append("有公积金");

        } else if (LoanTypeEnum.CREDIT_LOAN.getLoanName().equals(orderDO.getLoanType())) {
            sb.append(borrowerDO.getEduLevel()).append(" ");
            sb.append(borrowerDO.getProfession()).append(" ");
            sb.append("月收入" + borrowerDO.getMonthlyIncome() + "元").append(" ");
            sb.append(borrowerDO.getIncomeType()).append(" ");
            sb.append(borrowerDO.getCarInfo()).append(" ");
            sb.append(borrowerDO.getHouseInfo()).append(" ");
            if (!"无".equals(borrowerDO.getPersonalnsurance())) {
                sb.append(borrowerDO.getPersonalnsurance()).append(" ");
            }

        }
        model.setNotLoginIndexDesc(sb.toString());
    }
}
