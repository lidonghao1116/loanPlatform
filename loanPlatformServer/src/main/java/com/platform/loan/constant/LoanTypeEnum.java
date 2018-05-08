/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanTypeEnum.java, v 0.1 2018-05-08 下午11:50 caogu.wyp Exp $$
 */
public enum LoanTypeEnum {

    //
    WEI_LI_DAI("WEI_LI_DAI", "微粒贷"),
    //
    PROVIDENT_FUND("PROVIDENT_FUND", "公积金"),
    //
    SOCIAL_SECURITY("SOCIAL_SECURITY", "社保"),
    //
    CREDIT_LOAN("CREDIT_LOAN", "信用贷款");
    private String loanName;

    private String desc;

    private LoanTypeEnum(String loanName, String desc) {
        this.loanName = loanName;
        this.desc = desc;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
