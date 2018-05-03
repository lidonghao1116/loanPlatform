/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.request;

/**
 *
 * @author caogu.wyp
 * @version $Id: IndexRequest.java, v 0.1 2018-03-15 下午4:26 caogu.wyp Exp $$
 */
public class IndexRequest {

    private String requestId;

    private String exts;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }
}
