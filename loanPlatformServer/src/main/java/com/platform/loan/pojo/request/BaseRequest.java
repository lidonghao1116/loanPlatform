/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: BaseRequest.java, v 0.1 2018-05-05 上午10:43 caogu.wyp Exp $$
 */
public class BaseRequest implements Serializable {

    /** 请求扩展参数 */
    private Map<String, String> extParms;
    /** 请求的token，先从Authorization中取，再从request中 */
    private String              accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Map<String, String> getExtParms() {
        return extParms;
    }

    public void setExtParms(Map<String, String> extParms) {
        this.extParms = extParms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
