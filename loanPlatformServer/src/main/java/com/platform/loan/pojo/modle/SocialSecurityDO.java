/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.modle;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *  社保信息
 * @author caogu.wyp
 * @version $Id: SocialSecurityDO.java, v 0.1 2018-05-19 上午12:27 caogu.wyp Exp $$
 */
@Entity
@Table(name = "social_security")
public class SocialSecurityDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;
    private Timestamp createTime;
    private Timestamp modifyTime;

    /** 关联用户的手机号 */
    private String    phoneNo;

    private String    socialMessageId;

    @Column(columnDefinition = "TEXT")
    private String    socialDetail;

    @Column(columnDefinition = "TEXT")
    private String    socialReport;

    @Column(columnDefinition = "TEXT")
    private String    extData;

    public String getSocialMessageId() {
        return socialMessageId;
    }

    public void setSocialMessageId(String socialMessageId) {
        this.socialMessageId = socialMessageId;
    }

    public String getSocialDetail() {
        return socialDetail;
    }

    public void setSocialDetail(String socialDetail) {
        this.socialDetail = socialDetail;
    }

    public String getSocialReport() {
        return socialReport;
    }

    public void setSocialReport(String socialReport) {
        this.socialReport = socialReport;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
