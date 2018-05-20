/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.result.BaseResult;

import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderResult.java, v 0.1 2018-05-20 下午11:15 caogu.wyp Exp $$
 */
public class LoanOrderResult extends BaseResult{

    private List<LoanOrderViewModel> viewList;

    private int pageNum;

    public List<LoanOrderViewModel> getViewList() {
        return viewList;
    }

    public void setViewList(List<LoanOrderViewModel> viewList) {
        this.viewList = viewList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
