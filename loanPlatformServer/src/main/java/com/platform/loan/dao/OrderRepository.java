/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.OrderDO;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerRepository.java, v 0.1 2018-05-05 上午12:04 caogu.wyp Exp $$
 */
public interface OrderRepository extends CrudRepository<OrderDO, Integer> {

    public OrderDO findOrderDOByBorrowerPhoneNo(String borrowerPhoneNo);

    public OrderDO findOrderDObyBorrowerPhoneNoAndAndLoanType(String borrowerPhoneNo,String loanType);

    public OrderDO findOrderDOByOrderId(String orderId);

}
