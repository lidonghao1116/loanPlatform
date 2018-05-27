/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderRequest.java, v 0.1 2018-05-21 上午12:21 caogu.wyp Exp $$
 */
public class LoanOrderRequest extends BaseRequest {

    /** 页码，默认100条一页，从1开始 */
    @ApiModelProperty(value = "查询页码，从1页开始，每页最多返回100条，非法页码默认会查第1页", name = "pageNum", required = false)
    private String      pageNum;
    /** 这里的条件,是有无公积金,有无车,等,参考LoanOrderQueryCondition */
    @ApiModelProperty(value = "查询条件，前端传一个list过来，如果没有传，则默认查所有,这里的条件,是有无公积金,有无车,等,参考LoanOrderQueryConditionEnum", name = "queryCondition", required = false)
    private Set<String> queryCondition;

    @ApiModelProperty(value = "查询哪个城市的订单,不传则查询所有城市", name = "city", required = false)
    private String      city;

    private String      orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public Set<String> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Set<String> queryCondition) {
        this.queryCondition = queryCondition;
    }

}
