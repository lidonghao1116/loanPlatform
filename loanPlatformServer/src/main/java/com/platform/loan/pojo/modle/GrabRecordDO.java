/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *
 * @author caogu.wyp
 * @version $Id: GrabRecord.java, v 0.1 2018-05-28 下午10:25 caogu.wyp Exp $$
 */
@Entity
@Table(name = "grab_record")
public class GrabRecordDO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private String    managerPhoneNo;
    private String    orderId;
    private String    processResult;
    /** 抢的这条订单的处理状态 OrderProcessStatusEnum */
    private String    status;
    /** json格式 */
    @Column(columnDefinition = "TEXT")
    private String    extData;

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

    public String getManagerPhoneNo() {
        return managerPhoneNo;
    }

    public void setManagerPhoneNo(String managerPhoneNo) {
        this.managerPhoneNo = managerPhoneNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

}
