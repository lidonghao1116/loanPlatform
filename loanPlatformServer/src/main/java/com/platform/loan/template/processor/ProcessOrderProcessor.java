/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.ProcessResultEnum;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.ProcessOrderRequest;
import com.platform.loan.pojo.result.ProcessOrderResult;
import com.platform.loan.template.Processor;

/**
 *
 * @author caogu.wyp
 * @version $Id: ProcessOrderProcessor.java, v 0.1 2018-05-24 上午1:07 caogu.wyp Exp $$
 */
public class ProcessOrderProcessor implements Processor<ProcessOrderRequest, ProcessOrderResult> {
    @Override
    public void process(ProcessOrderRequest processOrderRequest,
                        ProcessOrderResult processOrderResult, Object... others) throws Exception {

        OrderRepository orderRepository = (OrderRepository) others[0];

        OrderDO orderDO = orderRepository.findOrderDO(processOrderRequest.getOrderId());

        if (null == orderDO) {
            throw new LoanPlatformException("不存在此订单~" + processOrderRequest.getOrderId());
        }

        // 根据desc将订单状态重置

        ProcessResultEnum processResultEnum = ProcessResultEnum.getByDesc(processOrderRequest
            .getProcessorResult());

        if (null == processResultEnum) {
            throw new LoanPlatformException("未定义的处理结果" + processOrderRequest.getProcessorResult());

        }
        orderDO.setProcessResult(processOrderRequest.getProcessorResult());
        orderDO.setOrderStatus(processResultEnum.getStatus());

        orderRepository.save(orderDO);
    }
}
