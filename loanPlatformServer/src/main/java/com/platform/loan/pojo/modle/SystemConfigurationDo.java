/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;

/**
 *  系统配置
 * @author caogu.wyp
 * @version $Id: SystemConfigurationDo.java, v 0.1 2018-05-06 上午12:25 caogu.wyp Exp $$
 */
@Entity
@Table(name = "system_configuration")
public class SystemConfigurationDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  key;

    private String  value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
