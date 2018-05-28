/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import com.platform.loan.pojo.modle.SysConfigurationDO;
import org.springframework.data.repository.CrudRepository;

public interface SysConfigRepository extends CrudRepository<SysConfigurationDO, Integer> {

    SysConfigurationDO findSysConfigurationDOByConfigKey(String configKey);
}
