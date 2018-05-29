/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.OrderDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerRepository.java, v 0.1 2018-05-05 上午12:04 caogu.wyp Exp $$
 */
public interface OrderRepository extends CrudRepository<OrderDO, Integer> {

    /**  重复发订单用到 */
    @Query(value = "SELECT * FROM borrower_order WHERE borrower_phone_no =?1 AND loan_type =?2 AND residue_grab_count > 0 ", nativeQuery = true)
    List<OrderDO> findOrderDO(String borrowerPhoneNo, String loanType);

    @Query(value = "select o from OrderDO o where o.orderId =?1")
    OrderDO findOrderDO(String orderId);

    /** 首页显示还可抢的订单 */
    @Query(value = "SELECT * FROM borrower_order WHERE residue_grab_count >0 ORDER BY create_time DESC", nativeQuery = true)
    List<OrderDO> findCanGrabOrders();

    @Query(value = "SELECT * FROM borrower_order WHERE order_id IN (?1) ORDER BY create_time DESC", nativeQuery = true)
    List<OrderDO> findOrders(Set<String> orderIds);

}
