/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.service.common;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author caogu.wyp
 */
public class CommonMethod {

    public static void verifyOTP(String phoneNo, String bizType, String smsCode)
                                                                                throws LoanPlatformException {

        String cacheCode = SimpleCacheUtil.getSMSCode(phoneNo, bizType);

        System.out.println("==手机发过来的:" + smsCode + ",缓存的:" + cacheCode);

        if (!StringUtils.equalsIgnoreCase(cacheCode, smsCode)) {
            throw new LoanPlatformException("短信验证码验证失败！");
        }
        //验证通过，清空缓存
        SimpleCacheUtil.removeSMSCode(phoneNo, bizType);
    }

    public static String buildLoginAccessToken(String phoneNo, String bizType)
                                                                              throws UnsupportedEncodingException {

        LoginSession loginSession = new LoginSession();
        loginSession.setPhoneNo(phoneNo);
        loginSession.setLoginUserType(bizType);

        return JwtUtil.createJwt(loginSession);
    }
}
