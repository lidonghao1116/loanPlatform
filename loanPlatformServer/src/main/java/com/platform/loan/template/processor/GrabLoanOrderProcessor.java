/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.BorrowerOrderStatusEnum;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.GrabLoanOrderRequest;
import com.platform.loan.pojo.result.GrabLoanOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: GrabLoanOrderProcessor.java, v 0.1 2018-05-23 上午12:56 caogu.wyp Exp $$
 */
public class GrabLoanOrderProcessor implements Processor<GrabLoanOrderRequest,GrabLoanOrderResult>{

    @Override
    public void process(GrabLoanOrderRequest grabLoanOrderRequest, GrabLoanOrderResult grabLoanOrderResult, Object... others)
            throws Exception {


        HttpServletRequest httpServletRequest = (HttpServletRequest)others[0];
        OrderRepository orderRepository = (OrderRepository)others[1];

        String managerPhoneNo= JwtUtil.getLoginSession(httpServletRequest).getPhoneNo();
        String orderId = grabLoanOrderRequest.getOrderId();

        OrderDO orderDO = orderRepository.findOrderDOByOrderId(orderId);

        if(null == orderDO){
            throw  new LoanPlatformException("订单不存在,订单id:"+orderId);
        }

        if(StringUtils.isBlank(managerPhoneNo)){
            throw  new LoanPlatformException("抢单时,信贷经理手机号为空!");
        }

        //TODO 抢单即改变订单状态
        orderDO.setManagerPhoneNo(managerPhoneNo);
        orderDO.setGrabTime(TimeUtil.getCurrentTimestamp());
        orderDO.setOrderStatus(BorrowerOrderStatusEnum.GRAB_FINISH.getStatus());
        orderRepository.save(orderDO);

        //check下订单是否抢成功
        OrderDO newOrderDO = orderRepository.findOrderDOByOrderId(orderId);
        if(!StringUtils.equalsAnyIgnoreCase(newOrderDO.getManagerPhoneNo(),managerPhoneNo)||
           StringUtils.equalsAnyIgnoreCase(BorrowerOrderStatusEnum.GRAB_FINISH.getStatus(),newOrderDO.getOrderStatus())){

            throw  new LoanPlatformException("信息对不上,抢单失败,可能被其他抢走了");
        }


    }
}
