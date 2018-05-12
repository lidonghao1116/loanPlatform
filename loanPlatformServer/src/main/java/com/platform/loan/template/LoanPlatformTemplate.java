/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanPlatformTemplate.java, v 0.1 2018-05-12 下午1:13 caogu.wyp Exp $$
 */
public class LoanPlatformTemplate<T extends BaseResult> {

    public static <T extends BaseResult> T run(Processor processor, BaseRequest request, T result,
                                               Object... others) {

        try {

            processor.process(request, result, others);

        } catch (Exception e) {

            System.out.println("exception:" + e.getMessage());
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }
}
