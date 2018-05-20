/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo;

import java.io.Serializable;

/**
 *
 * @author caogu.wyp
 * @version $Id: VerifyIdNoModel.java, v 0.1 2018-05-20 下午4:30 caogu.wyp Exp $$
 */
public class VerifyIdNoModel implements Serializable {

    private  String id_no;
    private  String id_name;

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getId_name() {
        return id_name;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
    }
}
