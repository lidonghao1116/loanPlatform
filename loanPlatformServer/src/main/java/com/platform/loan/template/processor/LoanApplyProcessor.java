package com.platform.loan.template.processor;

import com.platform.loan.pojo.request.LoanApplyRequest;
import com.platform.loan.pojo.result.LoanApplyResult;
import com.platform.loan.template.Processor;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanApplyProcessor.java, v 0.1 2018-05-12 下午3:50 caogu.wyp Exp $$
 */
public class LoanApplyProcessor implements Processor<LoanApplyRequest, LoanApplyResult> {
    @Override
    public void process(LoanApplyRequest request, LoanApplyResult loanApplyResult, Object... others)
                                                                                                    throws Exception {
        //TODO 
        System.out.println(request);

    }
}
