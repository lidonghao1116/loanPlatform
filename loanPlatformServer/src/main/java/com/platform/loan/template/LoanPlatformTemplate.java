/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template;

import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.util.LoanLogUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author caogu.wyp
 * @version $Id: LoanPlatformTemplate.java, v 0.1 2018-05-12 下午1:13 caogu.wyp Exp $$
 */
public class LoanPlatformTemplate {

    private static Set<String> noNeedPrintClass = new HashSet<String>();
    {
        noNeedPrintClass.add("LoanOrderResult");
        noNeedPrintClass.add("FrontDataResult");
        noNeedPrintClass.add("QueryLoginManagerOrderResult");
    }

    public static <T extends BaseResult> T run(Processor processor, BaseRequest request, T result,
                                               Object... others) {

        UUID uuid = UUID.randomUUID();
        long startTime = System.currentTimeMillis();
        try {
            logRequest(request, uuid);
            processor.process(request, result, others);

        } catch (Exception e) {

            LoanLogUtil.getLogger(LoanPlatformTemplate.class).error("processor.process error", e);
            //组装错误结果
            initExceptionResult(result, e);
        }

        logResult(result, uuid, startTime);
        return result;
    }

    private static void logRequest(BaseRequest request, UUID uuid) {
        LoanLogUtil.getLogger(LoanPlatformTemplate.class).info(
            request.getClass().getSimpleName() + "," + uuid + ",request:" + request.toString());
    }

    private static <T extends BaseResult> void logResult(T result, UUID uuid, long startTime) {

        if (isNeedPrint(result.getClass().getSimpleName())) {

            LoanLogUtil.getLogger(LoanPlatformTemplate.class).info(
                result.getClass().getSimpleName() + "," + "cost:"
                        + (System.currentTimeMillis() - startTime) + "ms," + uuid + ",result:"
                        + result.toString());
        }

    }

    private static boolean isNeedPrint(String simpleName) {

        if (noNeedPrintClass.contains(simpleName)) {
            return false;
        }
        return true;
    }

    private static <T extends BaseResult> void initExceptionResult(T result, Exception e) {
        if (e instanceof LoanPlatformException) {
            LoanPlatformException LoanPlatformException = (LoanPlatformException) e;
            if (null != LoanPlatformException.getResultCode()) {
                result.setResultCode(LoanPlatformException.getResultCode().getCode());
            }
        }
        result.setSuccess(Boolean.FALSE.toString());
        result.setResultMessage(e.getMessage());
    }
}
