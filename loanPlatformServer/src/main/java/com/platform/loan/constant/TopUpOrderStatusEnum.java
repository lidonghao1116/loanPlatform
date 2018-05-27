/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: TopUpOrderStatusEnum.java, v 0.1 2018-05-27 下午8:26 caogu.wyp Exp $$
 */
public enum TopUpOrderStatusEnum {
    //
    INIT("INIT", "初始化，在唤起支付工具时创建，这时并未付款，进入支付"),
    //
    PAY_SUCCESS("PAY_SUCCESS", "支付成功状态，在收到支付工具回调成功的函数时");

    private String code;
    private String desc;

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

    private TopUpOrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
