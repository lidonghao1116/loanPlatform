/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author caogu.wyp
 * @version $Id: ManagerLoginRequest.java, v 0.1 2018-05-22 上午12:52 caogu.wyp Exp $$
 */
public class ManagerLoginRequest extends BaseRequest {
    @ApiModelProperty(value = "登录手机号", name = "phoneNo", required = true)
    /** 手机号 */
    public String phoneNo;
    @ApiModelProperty(value = "短信验证码", name = "smsCode", required = true)
    /** 短信验证码 */
    public String smsCode;

    @ApiModelProperty(value = "信贷经理所在城市", name = "city", required = true)
    public String city;

    @ApiModelProperty(value = "所在公司公司", name = "company", required = true)
    public String company;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
