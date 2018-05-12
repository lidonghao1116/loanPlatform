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
    private Map<String, String> extRequestParms;

    public Map<String, String> getExtRequestParms() {
        return extRequestParms;
    }

    public void setExtRequestParms(Map<String, String> extRequestParms) {
        this.extRequestParms = extRequestParms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
