/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.dao;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 *  设置数据库表的引擎及编码，支持中文
 * @author caogu.wyp
 * @version $Id: MySQL5DialectUTF8.java, v 0.1 2018-05-10 上午12:59 caogu.wyp Exp $$
 */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
