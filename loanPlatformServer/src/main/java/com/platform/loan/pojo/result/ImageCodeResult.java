/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.result;

/**
 *
 * @author caogu.wyp
 * @version $Id: ImageCodeResult.java, v 0.1 2018-05-05 下午8:58 caogu.wyp Exp $$
 */
public class ImageCodeResult extends BaseResult {

    /** 图片验证码 */
    private String imageCode;

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }
}
