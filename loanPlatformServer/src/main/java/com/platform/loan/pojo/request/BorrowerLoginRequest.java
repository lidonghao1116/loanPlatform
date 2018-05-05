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
    public String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
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

    public static void main(String[] args) {
        BorrowerLoginRequest request = new BorrowerLoginRequest();

        request.setPhoneNo("12345667");

        System.out.println(request);

    }

}
