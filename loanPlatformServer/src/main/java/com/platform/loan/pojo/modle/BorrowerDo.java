/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  借款人个人信息
 * @author caogu.wyp
 * @version $Id: BorrowerDo.java, v 0.1 2018-05-04 下午11:59 caogu.wyp Exp $$
 */
@Entity
public class BorrowerDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  name;

}
