/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import com.platform.loan.constant.CommonConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author caogu.wyp
 */
@Entity
@Table(name = "borrower_order")
public class OrderDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer    id;
    private Timestamp  createTime;
    private Timestamp  modifyTime;
    private String     borrowerPhoneNo;
    /**  要贷多少钱，单位万 **/
    private String     loanLimit;
    private String     loanDeadline;
    private String     loanPurpose;
    private String     loanCity;
    /** 该笔单子价格 */
    private BigDecimal price;
    /** 类型，社保借款，公积金借款等 */
    private String     loanType;
    /** 订单号 */
    private String     orderId;
    private Integer    residueGrabCount = CommonConstants.ORDER_MAX_GRAB_COUNT;

    public Integer getResidueGrabCount() {
        return residueGrabCount;
    }

    public void setResidueGrabCount(Integer residueGrabCount) {
        this.residueGrabCount = residueGrabCount;
    }

    /** json格式 */
    @Column(columnDefinition = "TEXT")
    private String extData;

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

    public String getBorrowerPhoneNo() {
        return borrowerPhoneNo;
    }

    public void setBorrowerPhoneNo(String borrowerPhoneNo) {
        this.borrowerPhoneNo = borrowerPhoneNo;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
