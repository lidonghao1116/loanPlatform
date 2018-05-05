package com.platform.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 19890921@qq.com
 */
@SpringBootApplication
@EnableSwagger2
public class Starter {

    public static void main(String[] args) {

        SpringApplication.run(Starter.class, args);

    }
}
