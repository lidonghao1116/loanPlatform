package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanApplyRequest.java, v 0.1 2018-05-12 下午12:50 caogu.wyp Exp $$
 */
public class LoanApplyRequest extends BaseRequest {

    @ApiModelProperty(value = "借款类型，目前支持4种，/api/borrower/loantypes中获取具体值", name = "loanType", required = true)
    private String loanType;

    @ApiModelProperty(value = "借款额度，单位万", name = "loanLimit", required = true)
    private String loanLimit;

    @ApiModelProperty(value = "借款期限，单位月", name = "loanDeadline", required = true)
    private String loanDeadline;

    @ApiModelProperty(value = "借款目的", name = "loanPurpose", required = true)
    private String loanPurpose;

    @ApiModelProperty(value = "贷款所在城市", name = "loanCity", required = true)
    private String loanCity;
    /** 下面的数据,是信用贷款才需要传递的 */
    @ApiModelProperty(value = "公积金验证时间，没有填则认为未验证", name = "providentFundVerifyTime", required = false)
    private String providentFundVerifyTime;

    @ApiModelProperty(value = "社保验证时间，没有填则认为未验证", name = "socialSecurityVerifyTime", required = false)
    private String socialSecurityVerifyTime;
    //=======================//
    @ApiModelProperty(value = "文化程度，值服务端下发维护", name = "socialSecurityVerifyTime", required = false)
    private String eduLevel;

    @ApiModelProperty(value = "职业身份，值服务端下发维护", name = "socialSecurityVerifyTime", required = false)
    private String profession;

    @ApiModelProperty(value = "月收入，单位千", name = "monthlyIncome", required = false)
    private String monthlyIncome;

    @ApiModelProperty(value = "收入类型，值服务端下发维护", name = "incomeType", required = false)
    private String incomeType;

    @ApiModelProperty(value = "信用卡记录，值服务端下发维护", name = "creditRecord", required = false)
    private String creditRecord;

    @ApiModelProperty(value = "信用卡额度，值服务端下发维护", name = "creditLimit", required = false)
    private String creditLimit;

    @ApiModelProperty(value = "房产信息，值服务端下发维护", name = "houseInfo", required = false)
    private String houseInfo;

    @ApiModelProperty(value = "车子信息，值服务端下发维护", name = "carInfo", required = false)
    private String carInfo;

    @ApiModelProperty(value = "个人保险公司，值服务端下发维护 ", name = "insuranceCompany", required = false)
    private String insuranceCompany;

    @ApiModelProperty(value = "个人保险额度，值服务端下发维护 ", name = "insuranceCoverage", required = false)
    private String insuranceCoverage;

    @ApiModelProperty(value = "个人保险，值服务端下发维护 ", name = "personalnsurance", required = false)
    private String personalnsurance;

    @ApiModelProperty(value = "微粒贷总额", name = "weiLiDaiTotal", required = false)
    private String weiLiDaiTotal;

    @ApiModelProperty(value = "微粒贷可借额度", name = "weiLiDaiKeJie", required = false)
    private String weiLiDaiKeJie;

    public String getWeiLiDaiTotal() {
        return weiLiDaiTotal;
    }

    public void setWeiLiDaiTotal(String weiLiDaiTotal) {
        this.weiLiDaiTotal = weiLiDaiTotal;
    }

    public String getWeiLiDaiKeJie() {
        return weiLiDaiKeJie;
    }

    public void setWeiLiDaiKeJie(String weiLiDaiKeJie) {
        this.weiLiDaiKeJie = weiLiDaiKeJie;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(String loanLimit) {
        this.loanLimit = loanLimit;
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

    public String getLoanCity() {
        return loanCity;
    }

    public void setLoanCity(String loanCity) {
        this.loanCity = loanCity;
    }

    public String getProvidentFundVerifyTime() {
        return providentFundVerifyTime;
    }

    public void setProvidentFundVerifyTime(String providentFundVerifyTime) {
        this.providentFundVerifyTime = providentFundVerifyTime;
    }

    public String getSocialSecurityVerifyTime() {
        return socialSecurityVerifyTime;
    }

    public void setSocialSecurityVerifyTime(String socialSecurityVerifyTime) {
        this.socialSecurityVerifyTime = socialSecurityVerifyTime;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
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

    public String getPersonalnsurance() {
        return personalnsurance;
    }

    public void setPersonalnsurance(String personalnsurance) {
        this.personalnsurance = personalnsurance;
    }
}
