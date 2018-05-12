/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *  借款人个人信息
 * @author caogu.wyp
 * @version $Id: BorrowerDo.java, v 0.1 2018-05-04 下午11:59 caogu.wyp Exp $$
 */
@Entity
@Table(name = "borrower")
public class BorrowerDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;

    private Timestamp createTime;

    private Timestamp modifyTime;

    private String    idNo;

    private String    name;

    private String    phoneNo;

    private String    eduLevel;
    /** 职业 */
    private String    profession;

    /** 公积金认证时间 */
    private Timestamp providentFundVerifyTime;

    /** 社保认证时间 */
    private Timestamp socialSecurityVerifyTime;
    /** 月收入  */
    private String    monthlyIncome;
    /** 收入形式，现金发放，转帐工资，银行代发 */
    private String    incomeType;
    /** 信用记录 */
    private String    creditRecord;
    /** 信用卡额度 */
    private String    creditLimit;

    private String    houseInfo;

    private String    carInfo;

    private String    insuranceCompany;

    private String    insuranceCoverage;
    /** 个人保险时间,eg . 无/2年 */
    private String    personalnsurance;

    private String    extData;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public Timestamp getProvidentFundVerifyTime() {
        return providentFundVerifyTime;
    }

    public void setProvidentFundVerifyTime(Timestamp providentFundVerifyTime) {
        this.providentFundVerifyTime = providentFundVerifyTime;
    }

    public Timestamp getSocialSecurityVerifyTime() {
        return socialSecurityVerifyTime;
    }

    public void setSocialSecurityVerifyTime(Timestamp socialSecurityVerifyTime) {
        this.socialSecurityVerifyTime = socialSecurityVerifyTime;
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

    public String getPersonalnsurance() {
        return personalnsurance;
    }

    public void setPersonalnsurance(String personalnsurance) {
        this.personalnsurance = personalnsurance;
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

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
