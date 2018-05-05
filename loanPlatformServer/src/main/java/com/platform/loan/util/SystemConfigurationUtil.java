/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.platform.loan.constant.SystemConfigurationEnum;

/**
 *  获取系统参数
 * @author caogu.wyp
 * @version $Id: SystemConfigurationUtil.java, v 0.1 2018-05-06 上午12:53 caogu.wyp Exp $$
 */
public class SystemConfigurationUtil {

    public static String getSystemConfigurationValueByKey(SystemConfigurationEnum key) {

        return key.getDefaultValue();
    }

}
