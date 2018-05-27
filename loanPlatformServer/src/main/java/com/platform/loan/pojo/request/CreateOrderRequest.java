/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import java.math.BigDecimal;

/**
 *
 * @author caogu.wyp
 * @version $Id: CreateOrderRequest.java, v 0.1 2018-05-27 上午1:29 caogu.wyp Exp $$
 */
public class CreateOrderRequest extends BaseRequest {

    /** 充值铜币个数 */
    private BigDecimal count;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
