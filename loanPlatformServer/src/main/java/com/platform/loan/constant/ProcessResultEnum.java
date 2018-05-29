/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: ProcessResultEnum.java, v 0.1 2018-05-25 上午12:46 caogu.wyp Exp $$
 */
public enum ProcessResultEnum {

    //
    FORM_OTHER("FORM_OTHER", OrderProcessStatusEnum.INVALID.getCode(), "客户已从别处贷款"),
    //
    NOT_TRUE("NOT_TRUE", OrderProcessStatusEnum.INVALID.getCode(), "客户信息不真实"),
    //
    DO_NOT_WANT("DO_NOT_WANT", OrderProcessStatusEnum.INVALID.getCode(), "客户没有贷款意愿"),
    //
    I_CAN_NOT("I_CAN_NOT", OrderProcessStatusEnum.INVALID.getCode(), "客户我做不了"),
    //
    LOAN_SUCCESS("LOAN_SUCCESS", OrderProcessStatusEnum.LOAN_FINISH.getCode(), "已成功放款");

    private String code;
    private String status;
    private String desc;

    private ProcessResultEnum(String code, String status, String desc) {
        this.code = code;
        this.status = status;
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code </tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status </tt>.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc </tt>.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ProcessResultEnum getByDesc(String desc) {
        for (ProcessResultEnum processResultEnum : ProcessResultEnum.values()) {
            if (processResultEnum.getDesc().equals(desc)) {
                return processResultEnum;
            }
        }

        return null;
    }
}
