/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

import com.platform.loan.pojo.LoanOrderViewModel;

import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: QueryLoginManagerOrderResult.java, v 0.1 2018-05-24 上午1:04 caogu.wyp Exp $$
 */
public class QueryLoginManagerOrderResult extends BaseResult {

    private List<LoanOrderViewModel> viewList;

    public List<LoanOrderViewModel> getViewList() {
        return viewList;
    }

    public void setViewList(List<LoanOrderViewModel> viewList) {
        this.viewList = viewList;
    }
}
