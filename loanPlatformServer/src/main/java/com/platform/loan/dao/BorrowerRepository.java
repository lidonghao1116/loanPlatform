/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.BorrowerDo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerRepository.java, v 0.1 2018-05-05 上午12:04 caogu.wyp Exp $$
 */
public interface BorrowerRepository extends CrudRepository<BorrowerDo, Integer> {

    public BorrowerDo findBorrowerDoByPhoneNo(String phoneNo);

}
