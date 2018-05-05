/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.service;

import com.platform.loan.exception.LoanPlatformException;

/**
 *  短信服务
 * @author caogu.wyp
 * @version $Id: OtpService.java, v 0.1 2018-05-06 上午12:12 caogu.wyp Exp $$
 */
public interface OtpService {
    /**
     * 
     * @param phoneNo
     * @return
     */
    public void sendOtp(String phoneNo) throws LoanPlatformException;

}
