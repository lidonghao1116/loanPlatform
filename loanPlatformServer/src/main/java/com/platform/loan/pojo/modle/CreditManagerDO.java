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
 *  信贷经理个人信息
 * @author caogu.wyp
 * @version $Id: CreditManagerDo.java, v 0.1 2018-05-05 下午5:54 caogu.wyp Exp $$
 */
@Entity
@Table(name = "credit_manager")
public class CreditManagerDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer    id;
    private Timestamp  createTime;
    private Timestamp  modifyTime;
    //=====//
    private String     phoneNo;
    //信贷经理所在公司
    private String     company;
    //余额
    private BigDecimal balance;
    //城市
    private String     city;
    //微信号
    private String     webChatNo;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getBalance() {

        if (null == balance) {
            return new BigDecimal(0);
        }

        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebChatNo() {
        return webChatNo;
    }

    public void setWebChatNo(String webChatNo) {
        this.webChatNo = webChatNo;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
