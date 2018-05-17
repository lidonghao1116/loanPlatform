/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author caogu.wyp
 * @version $Id: VerifyIdNoRequest.java, v 0.1 2018-05-17 下午11:24 caogu.wyp Exp $$
 */
public class VerifyIdNoRequest extends BaseRequest {

    @ApiModelProperty(value = "身份证号", name = "idNo", required = true)
    private String idNo;

    @ApiModelProperty(value = "姓名", name = "name", required = true)
    private String name;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
