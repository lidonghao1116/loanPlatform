/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.platform.loan.constant.LoginUserTypeEnum;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.request.ManagerLoginRequest;
import com.platform.loan.pojo.request.OTPRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caogu.wyp
 * @version $Id: RequestCheckUtil.java, v 0.1 2018-05-06 下午6:16 caogu.wyp Exp $$
 */
public class RequestCheckUtil {

    private static boolean isIllegal(String... pars) {
        for (String par : pars) {
            if (StringUtils.isBlank(par)) {
                return true;
            }
        }
        return false;
    }

    public static void checkOtpRequest(OTPRequest request) throws LoanPlatformException {

        if (isIllegal(request.getPhoneNo())) {
            throw new LoanPlatformException("请求参数异常：" + request.toString());
        }

        if (isIllegalBizType(request.getBizType())) {
            throw new LoanPlatformException("OTPRequest 请求参数异常：，不支持此类bizType ："
                                            + request.getBizType());
        }

    }

    private static boolean isIllegalBizType(String bizType) {

        return null == LoginUserTypeEnum.getByCode(bizType) ? true : false;
    }

    public static void checkBorrowerLoginRequest(BorrowerLoginRequest request)
                                                                              throws LoanPlatformException {

        if (isIllegal(request.getPhoneNo(), request.getSmsCode())) {
            throw new LoanPlatformException("请求参数异常：" + request.toString());
        }

    }

    public static void checkManagerLoginRequest(ManagerLoginRequest request)
                                                                            throws LoanPlatformException {

        if (isIllegal(request.getPhoneNo(), request.getSmsCode())) {
            throw new LoanPlatformException("请求参数异常：" + request.toString());
        }

    }

}
