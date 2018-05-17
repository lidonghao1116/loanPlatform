/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanPlatformTemplate.java, v 0.1 2018-05-12 下午1:13 caogu.wyp Exp $$
 */
public class LoanPlatformTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanPlatformTemplate.class);

    public static <T extends BaseResult> T run(Processor processor, BaseRequest request, T result,
                                               Object... others) {

        try {

            processor.process(request, result, others);

        } catch (Exception e) {
            LOGGER.error("processor.process error", e);
            result.setSuccess(Boolean.FALSE.toString());
            result.setResultMessage(e.getMessage());
        }

        return result;
    }
}
