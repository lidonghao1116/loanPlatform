/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.pojo.request;

/**
 *
 * @author caogu.wyp
 * @version $Id: UpdateManagerInfoRequest.java, v 0.1 2018-05-24 下午11:46 caogu.wyp Exp $$
 */
public class UpdateManagerInfoRequest extends BaseRequest {

    //信贷经理所在公司
    private String company;
    //城市
    private String city;
    //微信号
    private String webChatNo;

    /**
     * Getter method for property <tt>company</tt>.
     *
     * @return property value of company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Setter method for property <tt>company </tt>.
     *
     * @param company value to be assigned to property company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Getter method for property <tt>city</tt>.
     *
     * @return property value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for property <tt>city </tt>.
     *
     * @param city value to be assigned to property city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter method for property <tt>webChatNo</tt>.
     *
     * @return property value of webChatNo
     */
    public String getWebChatNo() {
        return webChatNo;
    }

    /**
     * Setter method for property <tt>webChatNo </tt>.
     *
     * @param webChatNo value to be assigned to property webChatNo
     */
    public void setWebChatNo(String webChatNo) {
        this.webChatNo = webChatNo;
    }
}
