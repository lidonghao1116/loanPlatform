/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *  手机验证码
 * @author caogu.wyp
 * @version $Id: OTPRequest.java, v 0.1 2018-05-05 下午8:48 caogu.wyp Exp $$
 */
public class OTPRequest extends BaseRequest {

    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
