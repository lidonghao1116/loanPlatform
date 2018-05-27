/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoginUserType.java, v 0.1 2018-05-22 上午12:21 caogu.wyp Exp $$
 */
public enum LoginUserTypeEnum {
    //
    BORROWER("BORROWER", "借口人登陆"),
    //
    CREDIT_MANAGER("CREDIT_MANAGER", "信贷经理登陆");

    private String code;

    private String desc;

    private LoginUserTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static LoginUserTypeEnum getByCode(String code) {
        for (LoginUserTypeEnum loginUserTypeEnum : LoginUserTypeEnum.values()) {
            if (loginUserTypeEnum.getCode().equals(code)) {
                return loginUserTypeEnum;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
