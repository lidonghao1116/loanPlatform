/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author caogu.wyp
 * @version $Id: FilterRegistrations.java, v 0.1 2018-05-20 下午10:13 caogu.wyp Exp $$
 */
@Component
public class FilterRegistrations {


    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrosFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("crosFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginCheckFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("loginCheckFilter");
        registration.setOrder(2);
        return registration;
    }


}
