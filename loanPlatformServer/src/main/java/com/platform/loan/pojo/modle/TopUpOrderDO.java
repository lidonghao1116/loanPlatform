/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *  充值订单
 * @author caogu.wyp
 * @version $Id: TopUpOrder.java, v 0.1 2018-05-27 上午1:38 caogu.wyp Exp $$
 */
@Entity
@Table(name = "top_up_order")
public class TopUpOrderDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer    id;
    private Timestamp  createTime;
    private Timestamp  modifyTime;
    /** 到账时间 */
    private Timestamp  AccountingTime;
    /** 充值金额*/
    private BigDecimal amount;
    /** 对应支付宝的out_biz_no */
    private String     topUpOrderId;
    /** 订单状态 */
    private String     status;
    /** 充值人的手机号 */
    private String     phoneNo;

    @Column(columnDefinition = "TEXT")
    private String     alipayCallBackData;

    @Column(columnDefinition = "TEXT")
    private String     extData;

    public Timestamp getAccountingTime() {
        return AccountingTime;
    }

    public void setAccountingTime(Timestamp accountingTime) {
        AccountingTime = accountingTime;
    }

    public String getAlipayCallBackData() {
        return alipayCallBackData;
    }

    public void setAlipayCallBackData(String alipayCallBackData) {
        this.alipayCallBackData = alipayCallBackData;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTopUpOrderId() {
        return topUpOrderId;
    }

    public void setTopUpOrderId(String topUpOrderId) {
        this.topUpOrderId = topUpOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
