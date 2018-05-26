/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author caogu.wyp
 * @version $Id: ProcessOrderRequest.java, v 0.1 2018-05-24 上午1:07 caogu.wyp Exp $$
 */
public class ProcessOrderRequest extends BaseRequest {

    @ApiModelProperty(value = "订单id", name = "orderId", required = true)
    private String orderId;

    @ApiModelProperty(value = "处理结果", name = "processorResult", required = true)
    private String processorResult;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProcessorResult() {
        return processorResult;
    }

    public void setProcessorResult(String processorResult) {
        this.processorResult = processorResult;
    }
}
