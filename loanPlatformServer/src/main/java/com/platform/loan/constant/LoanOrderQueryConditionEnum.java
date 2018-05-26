/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderQueryCondition.java, v 0.1 2018-05-24 上午12:10 caogu.wyp Exp $$
 */
public enum LoanOrderQueryConditionEnum {

    //
    HAVE_CAR("HAVE_CAR", "有车"),
    //
    HAVE_HOUSE("HAVE_HOUSE", "有房产"),
    //
    HAVE_FUND("HAVE_FUND", "有公积金"),
    //
    HAVE_SOCIAL("HAVE_SOCIAL", "有社保"),
    //
    SERVANT("SERVANT", "公务员"),
    //
    HAVE_POLICY("HAVE_POLICY", "有保单"),
    //
    HAVE_CREDIT_CARD("HAVE_CREDIT_CARD", "有信用卡"),
    //
    HAVE_WEI_LI_DAI("HAVE_WEI_LI_DAI", "有微粒贷");

    private String code;
    private String desc;

    private LoanOrderQueryConditionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
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
