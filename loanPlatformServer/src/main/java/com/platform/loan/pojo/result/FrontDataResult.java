/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: FrontDataResult.java, v 0.1 2018-05-16 上午12:56 caogu.wyp Exp $$
 */
public class FrontDataResult extends BaseResult {

    private List<String> eduLevelList;
    private List<String> professionList;
    private List<String> incomeTypeList;
    private List<String> creditRecordList;
    private List<String> creditLimitList;
    private List<String> houseInfoList;
    private List<String> carInfoList;
    private List<String> insuranceCompanyList;
    private List<String> insuranceCoverageList;
    private List<String> personalnsuranceList;
    //==========//
    private List<String> resultCodeList;
    private List<String> loanOrderQueryConditionList;
    private List<String> borrowerOrderStatusList;
    private List<String> processResultList;

    /**
     * Getter method for property <tt>processResultList</tt>.
     *
     * @return property value of processResultList
     */
    public List<String> getProcessResultList() {
        return processResultList;
    }

    /**
     * Setter method for property <tt>processResultList </tt>.
     *
     * @param processResultList value to be assigned to property processResultList
     */
    public void setProcessResultList(List<String> processResultList) {
        this.processResultList = processResultList;
    }

    public List<String> getResultCodeList() {
        return resultCodeList;
    }

    public void setResultCodeList(List<String> resultCodeList) {
        this.resultCodeList = resultCodeList;
    }

    public List<String> getLoanOrderQueryConditionList() {
        return loanOrderQueryConditionList;
    }

    public void setLoanOrderQueryConditionList(List<String> loanOrderQueryConditionList) {
        this.loanOrderQueryConditionList = loanOrderQueryConditionList;
    }

    /**
     * Getter method for property <tt>borrowerOrderStatusList</tt>.
     *
     * @return property value of borrowerOrderStatusList
     */
    public List<String> getBorrowerOrderStatusList() {
        return borrowerOrderStatusList;
    }

    /**
     * Setter method for property <tt>borrowerOrderStatusList </tt>.
     *
     * @param borrowerOrderStatusList value to be assigned to property borrowerOrderStatusList
     */
    public void setBorrowerOrderStatusList(List<String> borrowerOrderStatusList) {
        this.borrowerOrderStatusList = borrowerOrderStatusList;
    }

    public List<String> getEduLevelList() {
        return eduLevelList;
    }

    public void setEduLevelList(List<String> eduLevelList) {
        this.eduLevelList = eduLevelList;
    }

    public List<String> getProfessionList() {
        return professionList;
    }

    public void setProfessionList(List<String> professionList) {
        this.professionList = professionList;
    }

    public List<String> getIncomeTypeList() {
        return incomeTypeList;
    }

    public void setIncomeTypeList(List<String> incomeTypeList) {
        this.incomeTypeList = incomeTypeList;
    }

    public List<String> getCreditRecordList() {
        return creditRecordList;
    }

    public void setCreditRecordList(List<String> creditRecordList) {
        this.creditRecordList = creditRecordList;
    }

    public List<String> getCreditLimitList() {
        return creditLimitList;
    }

    public void setCreditLimitList(List<String> creditLimitList) {
        this.creditLimitList = creditLimitList;
    }

    public List<String> getHouseInfoList() {
        return houseInfoList;
    }

    public void setHouseInfoList(List<String> houseInfoList) {
        this.houseInfoList = houseInfoList;
    }

    public List<String> getCarInfoList() {
        return carInfoList;
    }

    public void setCarInfoList(List<String> carInfoList) {
        this.carInfoList = carInfoList;
    }

    public List<String> getInsuranceCompanyList() {
        return insuranceCompanyList;
    }

    public void setInsuranceCompanyList(List<String> insuranceCompanyList) {
        this.insuranceCompanyList = insuranceCompanyList;
    }

    public List<String> getInsuranceCoverageList() {
        return insuranceCoverageList;
    }

    public void setInsuranceCoverageList(List<String> insuranceCoverageList) {
        this.insuranceCoverageList = insuranceCoverageList;
    }

    public List<String> getPersonalnsuranceList() {
        return personalnsuranceList;
    }

    public void setPersonalnsuranceList(List<String> personalnsuranceList) {
        this.personalnsuranceList = personalnsuranceList;
    }
}
