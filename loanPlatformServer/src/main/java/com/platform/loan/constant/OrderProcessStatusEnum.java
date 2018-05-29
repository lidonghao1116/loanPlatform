/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: OrderProcessStatusEnum.java, v 0.1 2018-05-28 下午10:40 caogu.wyp Exp $$
 */
public enum OrderProcessStatusEnum {
    //
    GRAB_FINISH("GRAB_FINISH", "抢到，待处理"),
    //
    LOAN_FINISH("LOAN_FINISH", "放贷完毕"),
    //
    INVALID("INVALID", "无效订单");

    private String code;
    private String desc;

    private OrderProcessStatusEnum(String code, String desc) {
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
