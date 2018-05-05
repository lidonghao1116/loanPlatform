/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  信贷经理个人信息
 * @author caogu.wyp
 * @version $Id: CreditManagerDo.java, v 0.1 2018-05-05 下午5:54 caogu.wyp Exp $$
 */
@Entity
public class CreditManagerDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
