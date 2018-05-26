/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *  手机验证码
 * @author caogu.wyp
 * @version $Id: OTPRequest.java, v 0.1 2018-05-05 下午8:48 caogu.wyp Exp $$
 */
public class OTPRequest extends BaseRequest {

    private String phoneNo;

    @ApiModelProperty(value = "图片验证码", name = "imageCode", required = true)
    public String  imageCode;

    /** 在请求生成验证码时，下发给前端的token，在response header中 CommonConstants.IMAGE_CODE_HEADER_KEY*/
    @ApiModelProperty(value = "图片验证码token,下发图片验证码时，放在hearders中的，key为IMAGE_CODE_HEADER_KEY", name = "imageCodeToken", required = true)
    public String  imageCodeToken;

    @ApiModelProperty(value = "发短信业务类型，用户端值为：BORROWER，信贷经理端为：CREDIT_MANAGER", name = "bizType", required = true)
    /** 业务类型  */
    public String  bizType;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageCodeToken() {
        return imageCodeToken;
    }

    public void setImageCodeToken(String imageCodeToken) {
        this.imageCodeToken = imageCodeToken;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
