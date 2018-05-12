/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.LoanTypeResult;
import com.platform.loan.template.Processor;

import java.util.ArrayList;
import java.util.List;

/**
 *  查询借贷类型
 * @author caogu.wyp
 * @version $Id: QueryLoanTypesProcessor.java, v 0.1 2018-05-12 下午3:09 caogu.wyp Exp $$
 */
public class QueryLoanTypesProcessor implements Processor<BaseRequest, LoanTypeResult> {

    @Override
    public void process(BaseRequest request, LoanTypeResult loanTypeResult, Object... others) {

        List<String> loans = new ArrayList<String>();

        for (LoanTypeEnum loanTypeEnum : LoanTypeEnum.values()) {
            loans.add(loanTypeEnum.getLoanName());
        }

        loanTypeResult.setLoanTypes(loans);
    }

}
