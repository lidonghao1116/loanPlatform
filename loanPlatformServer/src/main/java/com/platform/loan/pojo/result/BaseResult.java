/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: BaseResult.java, v 0.1 2018-05-05 上午10:44 caogu.wyp Exp $$
 */
public class BaseResult implements Serializable {

    /** true or false **/
    private String              success = "true";

    /**  结果信息 */
    private String              resultMessage;

    /** 扩展数据 */
    private Map<String, String> extResultData;

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
}
