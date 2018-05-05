/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import java.io.Serializable;

/**
 *
 * @author caogu.wyp
 * @version $Id: BaseResult.java, v 0.1 2018-05-05 上午10:44 caogu.wyp Exp $$
 */
public class BaseResult implements Serializable {

    /** true or false **/
    private String success = "true";

    /**  结果码，用于判断问题，参考ResultCode */
    private String resultCode;

    /**  结果信息 */
    private String resultMessage;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
