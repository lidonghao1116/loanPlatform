/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderViewModel.java, v 0.1 2018-05-21 上午12:23 caogu.wyp Exp $$
 */
public class LoanOrderViewModel implements Serializable {

        private String maskBorrowerName;
        private String maskPhoneNo;

        private String applyTime;
        private String loanCity;
        private String loanLimit;
        private String profession;
        private String  monthlyIncome;
        private String  incomeType;
        private boolean haveProvidentFund;
        private boolean haveSocialSecurity;
        private String houseInfo;
        private String carInfo;
        private String price;
        /** BorrowerOrderStatusEnum */
        private String orderStatus;
        private String loanType;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getMaskBorrowerName() {
        return maskBorrowerName;
    }

    public void setMaskBorrowerName(String maskBorrowerName) {
        this.maskBorrowerName = maskBorrowerName;
    }

    public String getMaskPhoneNo() {
        return maskPhoneNo;
    }

    public void setMaskPhoneNo(String maskPhoneNo) {
        this.maskPhoneNo = maskPhoneNo;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getLoanCity() {
        return loanCity;
    }

    public void setLoanCity(String loanCity) {
        this.loanCity = loanCity;
    }

    public String getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(String loanLimit) {
        this.loanLimit = loanLimit;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public boolean isHaveProvidentFund() {
        return haveProvidentFund;
    }

    public void setHaveProvidentFund(boolean haveProvidentFund) {
        this.haveProvidentFund = haveProvidentFund;
    }

    public boolean isHaveSocialSecurity() {
        return haveSocialSecurity;
    }

    public void setHaveSocialSecurity(boolean haveSocialSecurity) {
        this.haveSocialSecurity = haveSocialSecurity;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
