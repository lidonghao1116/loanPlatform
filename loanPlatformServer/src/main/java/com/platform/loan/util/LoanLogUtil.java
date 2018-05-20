/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.platform.loan.template.LoanPlatformTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author caogu.wyp
 * @version $Id: LogUtil.java, v 0.1 2018-05-05 上午10:50 caogu.wyp Exp $$
 */
public class LoanLogUtil {


    public static Logger getLogger(Class<?> clazz) {

        return LoggerFactory.getLogger(LoanPlatformTemplate.class);

    }

}
