/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *  错误码
 * @author caogu.wyp
 * @version $Id: ResultCode.java, v 0.1 2018-05-05 下午6:19 caogu.wyp Exp $$
 */
public enum ResultCode {

    SUCCESS("001", "", "请求成功");

    private String code;
    private String errorMessage;
    private String detail;

    private ResultCode(String code, String errorMessage, String detail) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
