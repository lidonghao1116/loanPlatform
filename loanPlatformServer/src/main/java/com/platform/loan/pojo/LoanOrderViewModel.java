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
    private String maskBorrowerPhoneNo;
    private String borrowerPhoneNo;
    private String borrowerName;
    private String applyTime;
    private String loanCity;
    private String loanLimit;
    private String profession;
    private String monthlyIncome;
    private String incomeType;
    private String houseInfo;
    private String carInfo;
    private String price;
    private String loanTypeDesc;
    /** 公积金taskId,用于显示用户公积金信息 */
    private String providentFundTaskId;
    /** 社保taskId,用于显示用户社保信息 */
    private String socialSecurityTaskId;
    private String orderId;
    /** 微粒贷 */
    private String weiLiDaiKeJie;
    private String weiLiDaiTotal;
    private String grabTime;

    /** 借款周期 */
    private String loanDeadline;
    /** 目的 */
    private String loanPurpose;

    private String borrowerIdNo;

    private String maskBorrowerIdNo;

    private String notLoginIndexDesc;

    private String personalnsurance;

    private String creditLimit;

    private String insuranceCompany;

    private String insuranceCoverage;

    private String creditRecord;

    private String eduLevel;

    private String providentFundCity;

    private String socialSecurityCity;

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getProvidentFundCity() {
        return providentFundCity;
    }

    public void setProvidentFundCity(String providentFundCity) {
        this.providentFundCity = providentFundCity;
    }

    public String getSocialSecurityCity() {
        return socialSecurityCity;
    }

    public void setSocialSecurityCity(String socialSecurityCity) {
        this.socialSecurityCity = socialSecurityCity;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(String insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public String getCreditRecord() {
        return creditRecord;
    }

    public void setCreditRecord(String creditRecord) {
        this.creditRecord = creditRecord;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPersonalnsurance() {
        return personalnsurance;
    }

    public void setPersonalnsurance(String personalnsurance) {
        this.personalnsurance = personalnsurance;
    }

    public String getMaskBorrowerIdNo() {
        return maskBorrowerIdNo;
    }

    public void setMaskBorrowerIdNo(String maskBorrowerIdNo) {
        this.maskBorrowerIdNo = maskBorrowerIdNo;
    }

    public String getNotLoginIndexDesc() {
        return notLoginIndexDesc;
    }

    public void setNotLoginIndexDesc(String notLoginIndexDesc) {
        this.notLoginIndexDesc = notLoginIndexDesc;
    }

    public String getBorrowerIdNo() {
        return borrowerIdNo;
    }

    public void setBorrowerIdNo(String borrowerIdNo) {
        this.borrowerIdNo = borrowerIdNo;
    }

    public String getLoanDeadline() {
        return loanDeadline;
    }

    public void setLoanDeadline(String loanDeadline) {
        this.loanDeadline = loanDeadline;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    /** 借贷方简介 */
    private String borrowerInfoDesc;

    public String getBorrowerInfoDesc() {
        return borrowerInfoDesc;
    }

    public void setBorrowerInfoDesc(String borrowerInfoDesc) {
        this.borrowerInfoDesc = borrowerInfoDesc;
    }

    public String getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(String grabTime) {
        this.grabTime = grabTime;
    }

    public String getWeiLiDaiKeJie() {
        return weiLiDaiKeJie;
    }

    public void setWeiLiDaiKeJie(String weiLiDaiKeJie) {
        this.weiLiDaiKeJie = weiLiDaiKeJie;
    }

    public String getWeiLiDaiTotal() {
        return weiLiDaiTotal;
    }

    public void setWeiLiDaiTotal(String weiLiDaiTotal) {
        this.weiLiDaiTotal = weiLiDaiTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getMaskBorrowerName() {
        return maskBorrowerName;
    }

    public void setMaskBorrowerName(String maskBorrowerName) {
        this.maskBorrowerName = maskBorrowerName;
    }

    public String getMaskBorrowerPhoneNo() {
        return maskBorrowerPhoneNo;
    }

    public void setMaskBorrowerPhoneNo(String maskBorrowerPhoneNo) {
        this.maskBorrowerPhoneNo = maskBorrowerPhoneNo;
    }

    public String getBorrowerPhoneNo() {
        return borrowerPhoneNo;
    }

    public void setBorrowerPhoneNo(String borrowerPhoneNo) {
        this.borrowerPhoneNo = borrowerPhoneNo;
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

    public String getLoanTypeDesc() {
        return loanTypeDesc;
    }

    public void setLoanTypeDesc(String loanTypeDesc) {
        this.loanTypeDesc = loanTypeDesc;
    }

    public String getProvidentFundTaskId() {
        return providentFundTaskId;
    }

    public void setProvidentFundTaskId(String providentFundTaskId) {
        this.providentFundTaskId = providentFundTaskId;
    }

    public String getSocialSecurityTaskId() {
        return socialSecurityTaskId;
    }

    public void setSocialSecurityTaskId(String socialSecurityTaskId) {
        this.socialSecurityTaskId = socialSecurityTaskId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
