/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *
 * @author caogu.wyp
 * @version $Id: AlipayPayRequest.java, v 0.1 2018-05-27 上午10:45 caogu.wyp Exp $$
 */
public class AlipayPayRequest extends BaseRequest {

    private String app_id;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}
