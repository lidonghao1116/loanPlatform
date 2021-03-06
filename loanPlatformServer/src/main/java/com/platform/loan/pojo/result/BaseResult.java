/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: BaseResult.java, v 0.1 2018-05-05 上午10:44 caogu.wyp Exp $$
 */
public class BaseResult implements Serializable {

    /** 结果码 */
    private String              resultCode;

    /** true or false **/
    private String              success = "true";

    /**  结果信息 */
    private String              resultMessage;

    /** 扩展数据 */
    private Map<String, String> extResultData;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Map<String, String> getExtResultData() {
        return extResultData;
    }

    public void setExtResultData(Map<String, String> extResultData) {
        this.extResultData = extResultData;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
