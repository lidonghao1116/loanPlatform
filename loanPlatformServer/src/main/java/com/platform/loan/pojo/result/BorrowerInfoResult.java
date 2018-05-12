package com.platform.loan.pojo.result;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerInfoResult.java, v 0.1 2018-05-09 下午11:19 caogu.wyp Exp $$
 */
public class BorrowerInfoResult extends BaseResult {

    private String idNo;

    private String name;

    private String phoneNo;

    @ApiModelProperty(value = "公积金认证时间,没有值则表示未认证", name = "providentFundVerifyTime")
    private String providentFundVerifyTime;

    @ApiModelProperty(value = "社保认证时间,没有值则表示未认证", name = "socialSecurityVerifyTime")
    private String socialSecurityVerifyTime;

    public String getProvidentFundVerifyTime() {
        return providentFundVerifyTime;
    }

    public void setProvidentFundVerifyTime(String providentFundVerifyTime) {
        this.providentFundVerifyTime = providentFundVerifyTime;
    }

    public String getSocialSecurityVerifyTime() {
        return socialSecurityVerifyTime;
    }

    public void setSocialSecurityVerifyTime(String socialSecurityVerifyTime) {
        this.socialSecurityVerifyTime = socialSecurityVerifyTime;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
