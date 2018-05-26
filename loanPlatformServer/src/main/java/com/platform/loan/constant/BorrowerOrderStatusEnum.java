/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerOrderEnum.java, v 0.1 2018-05-18 上午1:43 caogu.wyp Exp $$
 */
public enum BorrowerOrderStatusEnum {

    // AUDIT("AUDIT", "新创建，待审核订单"),
    //
    ENABLE_GRAB("ENABLE_GRAB", "可抢，待抢状态"),
    //
    GRAB_FINISH("GRAB_FINISH", "已抢"),
    //
    FINISH("FINISH", "完成，信贷经理抢完后，处理完成"),
    //
    INVALID("INVALID", "无效状态,垃圾单");

    private String status;
    private String desc;

    private BorrowerOrderStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
