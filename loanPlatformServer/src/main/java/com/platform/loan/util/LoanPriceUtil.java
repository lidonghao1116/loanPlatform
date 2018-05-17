/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.platform.loan.pojo.request.LoanApplyRequest;

import java.math.BigDecimal;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanPriceUtil.java, v 0.1 2018-05-18 上午1:54 caogu.wyp Exp $$
 */
public class LoanPriceUtil {

    public static BigDecimal getPrice(LoanApplyRequest request) {
        //TODO 这里会有定价规则
        return new BigDecimal(13.14);
    }
}
