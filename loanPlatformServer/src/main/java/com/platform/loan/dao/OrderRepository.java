/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.OrderDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerRepository.java, v 0.1 2018-05-05 上午12:04 caogu.wyp Exp $$
 */
public interface OrderRepository extends CrudRepository<OrderDO, Integer> {

    @Query(value = "select o from OrderDO o where o.borrowerPhoneNo =?1 and o.loanType=?2")
    OrderDO findOrderDO(String borrowerPhoneNo, String loanType);

    @Query(value = "select o from OrderDO o where o.orderId =?1")
    OrderDO findOrderDO(String orderId);

}
