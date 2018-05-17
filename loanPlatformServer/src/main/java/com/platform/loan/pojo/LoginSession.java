/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *  放jwt playload中的，不建议太多字段，仅放需要的
 * @author caogu.wyp
 * @version $Id: LoginSession.java, v 0.1 2018-05-08 下午10:36 caogu.wyp Exp $$
 */
public class LoginSession {

    private String phoneNo;

    /** 属于哪个业务的登录，目前分借款人及借贷经理 */
    private String biz;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
