/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanSpringConfigUtil.java, v 0.1 2018-05-27 下午12:09 caogu.wyp Exp $$
 */
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanSpringConfigUtil {

    /**  eg. http://xd.fenber.cn */
    public static String LOAN_FRONT_DOMAIN;

    public static String LOAN_SERVER_DOMAIN;

    @Value("${loan.front.domain}")
    public void setLoanDomain(String loanDomain) {
        LoanSpringConfigUtil.LOAN_FRONT_DOMAIN = loanDomain;
    }

    @Value("${loan.server.domain}")
    public void setLoanServerDomain(String loanServerDomain) {
        LoanSpringConfigUtil.LOAN_SERVER_DOMAIN = loanServerDomain;
    }

}
