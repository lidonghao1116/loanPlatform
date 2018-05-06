/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "borrower_order")
public class BorrowerOrderDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer    id;

    private String     borrowerIdNo;

    private String     loanLimit;

    private String     loanDeadline;

    private String     loanPurpose;

    private String     loanCity;

    private BigDecimal price;

    /**  待抢，已抢，取消，过期，完成，无效 */
    private String     orderStatus;

    /** 类型，社保借款，公积金借款等 */
    private String     orderType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrowerIdNo() {
        return borrowerIdNo;
    }

    public void setBorrowerIdNo(String borrowerIdNo) {
        this.borrowerIdNo = borrowerIdNo;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
