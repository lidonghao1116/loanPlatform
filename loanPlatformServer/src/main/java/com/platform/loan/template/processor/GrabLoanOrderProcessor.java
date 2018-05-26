package com.platform.loan.template.processor;

import com.platform.loan.constant.BorrowerOrderStatusEnum;
import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.GrabLoanOrderRequest;
import com.platform.loan.pojo.result.GrabLoanOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 *  抢单
 * @author caogu.wyp
 * @version $Id: GrabLoanOrderProcessor.java, v 0.1 2018-05-23 上午12:56 caogu.wyp Exp $$
 */
public class GrabLoanOrderProcessor implements Processor<GrabLoanOrderRequest, GrabLoanOrderResult> {

    @Override
    public void process(GrabLoanOrderRequest grabLoanOrderRequest,
                        GrabLoanOrderResult grabLoanOrderResult, Object... others) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        OrderRepository orderRepository = (OrderRepository) others[1];
        ManagerRepository managerRepository = (ManagerRepository) others[2];
        String managerPhoneNo = JwtUtil.getLoginSession(httpServletRequest).getPhoneNo();
        String orderId = grabLoanOrderRequest.getOrderId();

        OrderDO orderDO = orderRepository.findOrderDO(orderId);

        if (null == orderDO) {
            throw new LoanPlatformException("订单不存在,订单id:" + orderId);
        }
        if (!BorrowerOrderStatusEnum.ENABLE_GRAB.getStatus().equals(orderDO.getOrderStatus())) {
            //不是可抢状态
            throw new LoanPlatformException("订单状态不可抢," + orderDO.getOrderStatus());
        }

        CreditManagerDO creditManagerDO = managerRepository
            .findCreditManagerDOByPhoneNo(managerPhoneNo);

        BigDecimal newBalance = creditManagerDO.getBalance().subtract(orderDO.getPrice());

        if (-1 == newBalance.compareTo(new BigDecimal(0))) {

            throw new LoanPlatformException(ResultCodeEnum.NOT_SUFFICIENT_BALANCE, "余额不足,请充值!");

        }
        saveToDb(orderRepository, managerRepository, managerPhoneNo, orderDO, creditManagerDO,
            newBalance);

        initResult(newBalance, orderDO.getPrice(), grabLoanOrderResult);

    }

    private void initResult(BigDecimal newBalance, BigDecimal price,
                            GrabLoanOrderResult grabLoanOrderResult) {

        grabLoanOrderResult.setBalance(newBalance.toString());
        grabLoanOrderResult.setPrice(price.toString());

    }

    private void saveToDb(OrderRepository orderRepository, ManagerRepository managerRepository,
                          String managerPhoneNo, OrderDO orderDO, CreditManagerDO creditManagerDO,
                          BigDecimal newBalance) {
        //TODO 这里后续要数据库加锁及事务
        orderDO.setManagerPhoneNo(managerPhoneNo);
        orderDO.setGrabTime(TimeUtil.getCurrentTimestamp());
        orderDO.setOrderStatus(BorrowerOrderStatusEnum.GRAB_FINISH.getStatus());
        creditManagerDO.setBalance(newBalance);
        orderRepository.save(orderDO);
        managerRepository.save(creditManagerDO);
    }
}
