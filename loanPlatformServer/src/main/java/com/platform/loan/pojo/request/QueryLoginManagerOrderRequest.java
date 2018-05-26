/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 *
 * @author caogu.wyp
 * @version $Id: QueryLoginManagerOrderRequest.java, v 0.1 2018-05-24 上午1:04 caogu.wyp Exp $$
 */
public class QueryLoginManagerOrderRequest extends BaseRequest {

    @ApiModelProperty(value = "已登录经理查询他的订单,条件为订单的状态,不传则查询所有,GRAB_FINISH:未处理,FINISH:已处理,INVALID:垃圾单", name = "queryCondition", required = true)
    private Set<String> queryCondition;

    public Set<String> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Set<String> queryCondition) {
        this.queryCondition = queryCondition;
    }
}
