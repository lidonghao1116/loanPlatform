package com.platform.loan.pojo.result;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerInfoResult.java, v 0.1 2018-05-09 下午11:19 caogu.wyp Exp $$
 */
public class BorrowerInfoResult extends BaseResult {

    private String idNo;

    private String name;

    private String phoneNo;

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
