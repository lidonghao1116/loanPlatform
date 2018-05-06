/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 *  借款人个人信息
 * @author caogu.wyp
 * @version $Id: BorrowerDo.java, v 0.1 2018-05-04 下午11:59 caogu.wyp Exp $$
 */
@Entity
@Table(name = "borrower")
public class BorrowerDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  idNo;

    private String  name;

    private String  phoneNo;

    private String  eduLevel;

    private String  profession;

    private String  providentFund;

    private String  socialSecurity;

    private String  monthlyIncome;

    private String  incomeType;
    /** 信用记录 */
    private String  creditRecord;
    /** 信用卡额度 */
    private String  creditLimit;

    private String  haveHouse;

    private String  haveCar;

    private String  insuranceCompany;

    private String  insuranceCoverage;

    private String  ext_field;

    private String  creditDetai;

    private Date    createTime;

    private Date    modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(String providentFund) {
        this.providentFund = providentFund;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
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

    public String getHaveHouse() {
        return haveHouse;
    }

    public void setHaveHouse(String haveHouse) {
        this.haveHouse = haveHouse;
    }

    public String getHaveCar() {
        return haveCar;
    }

    public void setHaveCar(String haveCar) {
        this.haveCar = haveCar;
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

    public String getExt_field() {
        return ext_field;
    }

    public void setExt_field(String ext_field) {
        this.ext_field = ext_field;
    }

    public String getCreditDetai() {
        return creditDetai;
    }

    public void setCreditDetai(String creditDetai) {
        this.creditDetai = creditDetai;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
