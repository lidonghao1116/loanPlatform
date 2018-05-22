/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.exception;

import com.platform.loan.constant.ResultCodeEnum;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanPlatformException.java, v 0.1 2018-05-05 上午10:47 caogu.wyp Exp $$
 */
public class LoanPlatformException extends Exception {
    /**
     * Constructs a new exception with the specified detail message and cause.  <p>Note that the detail message associated with {@code
     * cause} is <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.4
     */
    private ResultCodeEnum resultCode;

    public ResultCodeEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    public LoanPlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.  The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public LoanPlatformException(String message) {
        super(message);
    }

    public LoanPlatformException(ResultCodeEnum resultCode , String message){
        super(message);
        this.resultCode = resultCode;
    }
}
