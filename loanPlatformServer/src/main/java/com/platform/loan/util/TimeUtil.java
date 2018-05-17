/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author caogu.wyp
 * @version $Id: TimeUtil.java, v 0.1 2018-05-18 上午1:51 caogu.wyp Exp $$
 */
public class TimeUtil {

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

}
