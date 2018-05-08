package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  借款人个人信息
 * @author caogu.wyp
 * @version $Id: BorrowerDo.java, v 0.1 2018-05-04 下午11:59 caogu.wyp Exp $$
 */
@Entity
@Table(name = "sys_configuration")
public class SysConfigurationDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
