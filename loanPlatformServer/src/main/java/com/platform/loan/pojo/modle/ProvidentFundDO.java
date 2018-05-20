/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *  公积金信息
 * @author caogu.wyp
 * @version $Id: ProvidentFundDO.java, v 0.1 2018-05-19 上午12:26 caogu.wyp Exp $$
 */
@Entity
@Table(name = "provident_fund")
public class ProvidentFundDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;
    private Timestamp createTime;
    private Timestamp modifyTime;
    //==========//
    //关联用户的手机号
    private String    phoneNo;

    /** 公积金中心查询来的数据，json格式 */
    @Column(columnDefinition="TEXT")
    private String extData;

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
