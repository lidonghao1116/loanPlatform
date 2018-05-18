/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *
 * @author caogu.wyp
 * @version $Id: ProvidentFundTaskSubmitRequest.java, v 0.1 2018-05-19 上午1:06 caogu.wyp Exp $$
 */
public class CallBackRequest extends BaseRequest {

    /**
     1.任务创建通知（task.submit）：授权申请成功，魔蝎调用客户在租户管理平台上配置的“任务创建通知URL”，异步通知客户任务创建成功；（受理申请失败的情况下，不会下发此通知）

     2.任务授权登录状态通知（task）：授权登录完成，魔蝎调用客户在租户管理平台上配置的“任务授权登录状态通知URL”，异步通知客户授权结果，客户根据回调消息中“result”字段判断授权登录成功或失败，授权登录失败的情况，客户可对用户授权状态重置；

     3.任务采集失败通知（task.fail）：数据采集失败，魔蝎调用客户在租户管理平台上配置的“任务采集失败通知URL”，异步通知客户数据采集失败，客户可对用户授权状态重置；

     4.账单通知（bill）：数据采集完成，魔蝎调用客户在租户管理平台上配置的“账单通知URL”，异步通知客户数据采集成功，客户可调用“获取公积金数据”接口获取原始数据；

     5.用户报告通知（report）：报告处理完成，魔蝎调用客户在租户管理平台上配置的“用户报告通知URL”，异步通知客户报告处理结果，客户根据回调消息中“result”字段判断报告处理成功或失败，“result”=true报告处理成功，客户可调用“获取公积金报告接口”获取用户报告，“result”=false报告处理失败，客户可对用户授权状态重置。
     */

    private String username;
    private String timestamp;
    private String task_id;
    private String user_id;
    private String result;
    private String message;
    private String area_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }
}
