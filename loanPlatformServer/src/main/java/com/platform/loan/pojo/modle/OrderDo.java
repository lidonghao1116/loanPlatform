/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;

/**
 *  借款人发布的订单
 * @author caogu.wyp
 * @version $Id: OrderDo.java, v 0.1 2018-05-05 下午5:48 caogu.wyp Exp $$
 */
@Entity
@Table(name = "order")
public class OrderDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
