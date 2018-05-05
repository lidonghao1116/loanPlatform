/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;

/**
 *  借款人个人信息
 * @author caogu.wyp
 * @version $Id: BorrowerDo.java, v 0.1 2018-05-04 下午11:59 caogu.wyp Exp $$
 */
@Entity
@Table(name = "borrower")
public class BorrowerDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
