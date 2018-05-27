/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

/**
 *
 * @author caogu.wyp
 * @version $Id: CreateOrderResult.java, v 0.1 2018-05-27 下午4:36 caogu.wyp Exp $$
 */
public class CreateOrderResult extends BaseResult {

    private String payForm;

    public String getPayForm() {
        return payForm;
    }

    public void setPayForm(String payForm) {
        this.payForm = payForm;
    }
}
