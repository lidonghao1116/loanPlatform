/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util;

import com.platform.loan.constant.Swagger2Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *  自动生成api文档，调试
 * @author caogu.wyp
 * @version $Id: Swagger2.java, v 0.1 2018-05-05 上午1:14 caogu.wyp Exp $$
 */
@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage(Swagger2Constants.SCAN_PACKAGE_NAME))
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(Swagger2Constants.TITLE)
            .description(Swagger2Constants.DESCRIPTION).termsOfServiceUrl(Swagger2Constants.URL)
            .version(Swagger2Constants.API_VERSION_1).build();
    }
}
