/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.TopUpOrderDO;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caogu.wyp
 * @version $Id: TopUpOrderRepository.java, v 0.1 2018-05-27 下午4:42 caogu.wyp Exp $$
 */
public interface TopUpOrderRepository extends CrudRepository<TopUpOrderDO, Integer> {

    TopUpOrderDO queryTopUpOrderDOByTopUpOrderId(String topUpOrderId);
}
