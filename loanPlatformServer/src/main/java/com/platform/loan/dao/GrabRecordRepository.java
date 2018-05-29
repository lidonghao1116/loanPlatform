/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.GrabRecordDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrabRecordRepository extends CrudRepository<GrabRecordDO, Integer> {

    @Query(value = "select * from grab_record where order_id = ?1 AND manager_phone_no = ?2", nativeQuery = true)
    GrabRecordDO findGrabRecordDO(String orderId, String managerPhoneNo);

    @Query(value = "select * from grab_record where manager_phone_no = ?1", nativeQuery = true)
    List<GrabRecordDO> findGrabRecords(String managerPhoneNo);

}
