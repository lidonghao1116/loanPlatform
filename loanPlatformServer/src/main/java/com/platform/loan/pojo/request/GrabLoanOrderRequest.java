/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *
 * @author caogu.wyp
 * @version $Id: GrabLoanOrderRequest.java, v 0.1 2018-05-23 上午12:56 caogu.wyp Exp $$
 */
public class GrabLoanOrderRequest extends  BaseRequest{

    /** 订单号 */
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
