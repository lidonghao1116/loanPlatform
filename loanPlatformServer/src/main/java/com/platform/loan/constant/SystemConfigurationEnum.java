/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *  对应db中SystemConfiguration表的key
 * @author caogu.wyp
 * @version $Id: SystemConfigurationEnum.java, v 0.1 2018-05-06 上午12:33 caogu.wyp Exp $$
 */
public enum SystemConfigurationEnum {
    //
    CHUANG_LAN_ACCOUNT("chuang_lan_account", "N3472112", "创蓝平台的短信账号"),
    //
    CHUANG_LAN_PASSWORD("chuang_lan_password", "wjETyDxFzt6b3b", "创蓝平台的发短信密码"),
    //
    CHUANG_LAN_URL("chuang_lan_url", "http://smssh1.253.com/msg/send/json", "创蓝平台的url"),
    //
    CHUANG_LAN_SMS_TEMP("chuang_lan__sms_temp", "你好，你的短信验证码为：", "验证码短信模版，只能自定义最前部分，验证码append在最后面");

    private String key;

    private String defaultValue;

    private String desc;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private SystemConfigurationEnum(String key, String defaultValue, String desc) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }
}
