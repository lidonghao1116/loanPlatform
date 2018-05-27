/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import java.math.BigDecimal;

/**
 *
 * @author caogu.wyp
 * @version $Id: QueryLoginManagerResult.java, v 0.1 2018-05-24 上午1:06 caogu.wyp Exp $$
 */
public class QueryLoginManagerResult extends BaseResult {

    private String     phoneNo;
    //信贷经理所在公司
    private String     company;
    //余额
    private BigDecimal balance;
    //城市
    private String     city;
    //微信号
    private String     webChatNo;

    private boolean    haveCertfication;

    public boolean isHaveCertfication() {
        return haveCertfication;
    }

    public void setHaveCertfication(boolean haveCertfication) {
        this.haveCertfication = haveCertfication;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getBalance() {
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
}
