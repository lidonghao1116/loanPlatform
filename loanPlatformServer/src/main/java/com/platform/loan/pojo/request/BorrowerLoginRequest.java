/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *  借款人登录请求
 * @author caogu.wyp
 * @version $Id: BorrowerLoginRequest.java, v 0.1 2018-05-05 下午6:11 caogu.wyp Exp $$
 */
public class BorrowerLoginRequest extends BaseRequest {

    /** 手机号 */
    public String phoneNo;

    /** 短信验证码 */
    public String SMSCode;

    /**  图片验证码 */
    public String imageCode;

    /** 在请求生成验证码时，下发给前端的token，在header中 */
    public String imageCodeToken;

    public String getImageCodeToken() {
        return imageCodeToken;
    }

    public void setImageCodeToken(String imageCodeToken) {
        this.imageCodeToken = imageCodeToken;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

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
