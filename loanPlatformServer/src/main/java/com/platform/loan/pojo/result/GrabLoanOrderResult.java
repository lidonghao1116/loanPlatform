/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

/**
 *
 * @author caogu.wyp
 * @version $Id: GrabLoanOrderResult.java, v 0.1 2018-05-23 上午12:56 caogu.wyp Exp $$
 */
public class GrabLoanOrderResult extends BaseResult {

    private String balance;
    private String price;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
