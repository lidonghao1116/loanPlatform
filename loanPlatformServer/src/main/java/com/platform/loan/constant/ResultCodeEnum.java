/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *  错误码
 * @author caogu.wyp
 * @version $Id: ResultCodeEnum.java, v 0.1 2018-05-05 下午6:19 caogu.wyp Exp $$
 */
public enum ResultCodeEnum {
    SUCCESS("000", "请求成功", "请求成功"), TOKEN_VERIFY_FAILED("001", "token验证失败", "token验证失败"), LOAN_REPEAT(
                                                                                                      "002",
                                                                                                      "重复借款",
                                                                                                      "一个用户已申请过该类型或者其他类型借款"), NOT_SUFFICIENT_BALANCE(
                                                                                                                                                     "003",
                                                                                                                                                     "抢订单时余额不足",
                                                                                                                                                     "抢订单时余额不足");

    private String code;
    private String errorMessage;
    private String detail;

    private ResultCodeEnum(String code, String errorMessage, String detail) {
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
