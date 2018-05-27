/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerLoginResult.java, v 0.1 2018-05-09 上午12:31 caogu.wyp Exp $$
 */
public class LoginResult extends BaseResult {

    /** 登录后下发给前端的jwt token */
    private String  accessToken;

    private boolean haveVerifyIdNo;

    public boolean isHaveVerifyIdNo() {
        return haveVerifyIdNo;
    }

    public void setHaveVerifyIdNo(boolean haveVerifyIdNo) {
        this.haveVerifyIdNo = haveVerifyIdNo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
