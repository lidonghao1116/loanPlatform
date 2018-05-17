/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *  借款人登录请求
 * @author caogu.wyp
 * @version $Id: BorrowerLoginRequest.java, v 0.1 2018-05-05 下午6:11 caogu.wyp Exp $$
 */
public class BorrowerLoginRequest extends BaseRequest {

    @ApiModelProperty(value = "登录手机号", name = "phoneNo", required = true)
    /** 手机号 */
    public String phoneNo;
    @ApiModelProperty(value = "短信验证码", name = "SMSCode", required = true)
    /** 短信验证码 */
    public String SMSCode;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSMSCode() {
        return SMSCode;
    }

    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }

}
