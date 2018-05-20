/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderRequest.java, v 0.1 2018-05-21 上午12:21 caogu.wyp Exp $$
 */
public class LoanOrderRequest extends BaseRequest {

    /** 页码，默认100条一页，从1开始 */
    private String pageNum;
    /** 没有条件，则查询所有订单 */
    private Map<String,String> queryCondition;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public Map<String, String> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Map<String, String> queryCondition) {
        this.queryCondition = queryCondition;
    }
}
