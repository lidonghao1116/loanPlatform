package com.platform.loan.pojo.result;

import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanTypeResult.java, v 0.1 2018-05-08 下午11:46 caogu.wyp Exp $$
 */
public class LoanTypeResult extends BaseResult {

    /** 借贷类型，公积金，微粒贷等 */
    private List<String> loanTypes;

    public List<String> getLoanTypes() {
        return loanTypes;
    }

    public void setLoanTypes(List<String> loanTypes) {
        this.loanTypes = loanTypes;
    }
}
