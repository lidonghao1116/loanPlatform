/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template;

import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.util.LoanLogUtil;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanPlatformTemplate.java, v 0.1 2018-05-12 下午1:13 caogu.wyp Exp $$
 */
public class LoanPlatformTemplate {

    public static <T extends BaseResult> T run(Processor processor, BaseRequest request, T result,
                                               Object... others) {

        try {

            processor.process(request, result, others);

        } catch (Exception e) {

            LoanLogUtil.getLogger(LoanPlatformTemplate.class).error("processor.process error", e);
            //组装错误结果
            initExceptionResult(result, e);
        }

        return result;
    }

    private static <T extends BaseResult> void initExceptionResult(T result, Exception e) {
        if(e instanceof LoanPlatformException){
            LoanPlatformException LoanPlatformException = (LoanPlatformException)e;
            if(null != LoanPlatformException.getResultCode()){
                result.setResultCode(LoanPlatformException.getResultCode().getCode());
            }
        }
        result.setSuccess(Boolean.FALSE.toString());
        result.setResultMessage(e.getMessage());
    }
}
