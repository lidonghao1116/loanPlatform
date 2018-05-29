package com.platform.loan.template.processor;

import com.platform.loan.constant.OrderProcessStatusEnum;
import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.dao.GrabRecordRepository;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.modle.GrabRecordDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.GrabLoanOrderRequest;
import com.platform.loan.pojo.result.GrabLoanOrderResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 抢单
 *
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
        GrabRecordRepository grabRecordRepository = (GrabRecordRepository) others[3];

        String managerPhoneNo = JwtUtil.getLoginSession(httpServletRequest).getPhoneNo();
        String orderId = grabLoanOrderRequest.getOrderId();
        //查询订单
        OrderDO orderDO = getOrderDO(orderRepository, orderId);
        //查询信贷经理
        CreditManagerDO creditManagerDO = getCreditManagerDO(managerRepository, managerPhoneNo);
        //预扣减余额，判断余额是否足够
        BigDecimal newBalance = getBigDecimal(orderDO, creditManagerDO);
        /** 下面三步需要一致性 */
        insertGrabRecord(grabRecordRepository, managerPhoneNo, orderId);
        updateOrderGrabCount(orderRepository, orderDO);
        updateManagerBalance(managerRepository, newBalance, creditManagerDO);

        initResult(newBalance, orderDO.getPrice(), grabLoanOrderResult);

    }

    private void updateManagerBalance(ManagerRepository managerRepository, BigDecimal newBalance,
                                      CreditManagerDO creditManagerDO) {
        creditManagerDO.setBalance(newBalance);
        managerRepository.save(creditManagerDO);
    }

    private void updateOrderGrabCount(OrderRepository orderRepository, OrderDO orderDO) {
        orderDO.setResidueGrabCount(orderDO.getResidueGrabCount() - 1);
        orderRepository.save(orderDO);
    }

    private void insertGrabRecord(GrabRecordRepository grabRecordRepository, String managerPhoneNo,
                                  String orderId) {
        //检查自己未抢过这笔订单
        GrabRecordDO grabRecordDOS = grabRecordRepository.findGrabRecordDO(orderId, managerPhoneNo);

        if (null != grabRecordDOS) {
            throw new LoanPlatformException("抢订单失败，你已抢过该订单，请勿重复抢单～");
        }

        GrabRecordDO newGrabRecordDO = new GrabRecordDO();
        newGrabRecordDO.setManagerPhoneNo(managerPhoneNo);
        newGrabRecordDO.setOrderId(orderId);
        newGrabRecordDO.setStatus(OrderProcessStatusEnum.GRAB_FINISH.getCode());
        newGrabRecordDO.setCreateTime(TimeUtil.getCurrentTimestamp());
        newGrabRecordDO.setModifyTime(TimeUtil.getCurrentTimestamp());
        grabRecordRepository.save(newGrabRecordDO);

    }

    private BigDecimal getBigDecimal(OrderDO orderDO, CreditManagerDO creditManagerDO) {
        BigDecimal newBalance = creditManagerDO.getBalance().subtract(orderDO.getPrice());

        if (-1 == newBalance.compareTo(new BigDecimal(0))) {

            throw new LoanPlatformException(ResultCodeEnum.NOT_SUFFICIENT_BALANCE, "余额不足,请充值!");
        }
        return newBalance;
    }

    private OrderDO getOrderDO(OrderRepository orderRepository, String orderId) {
        OrderDO orderDO = orderRepository.findOrderDO(orderId);

        if (null == orderDO) {
            throw new LoanPlatformException("订单不存在,订单id:" + orderId);
        }
        if (orderDO.getResidueGrabCount() <= 0) {
            throw new LoanPlatformException("订单不可抢，剩余次数为：" + orderDO.getResidueGrabCount());
        }
        return orderDO;
    }

    private CreditManagerDO getCreditManagerDO(ManagerRepository managerRepository,
                                               String managerPhoneNo) {
        CreditManagerDO creditManagerDO = managerRepository
            .findCreditManagerDOByPhoneNo(managerPhoneNo);

        if (null == creditManagerDO) {

            throw new LoanPlatformException("未在数据库中找到该用户，请重新正常流程登录！");
        }

        if (StringUtils.isBlank(creditManagerDO.getIdNo())) {
            throw new LoanPlatformException(ResultCodeEnum.NOT_CERTFICATION, "未实名认证！");
        }
        return creditManagerDO;
    }

    private void initResult(BigDecimal newBalance, BigDecimal price,
                            GrabLoanOrderResult grabLoanOrderResult) {

        grabLoanOrderResult.setBalance(newBalance.toString());
        grabLoanOrderResult.setPrice(price.toString());

    }

    private void saveToDb(OrderRepository orderRepository, ManagerRepository managerRepository,
                          String managerPhoneNo, OrderDO orderDO, CreditManagerDO creditManagerDO,
                          BigDecimal newBalance) {

        /**
         * 1 新增一条抢单记录    
         * 2 订单次数减1
         * 3 修改信贷经理余额
         */

    }
}
