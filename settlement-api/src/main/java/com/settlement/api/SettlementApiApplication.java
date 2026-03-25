package com.settlement.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 清算系统API启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.settlement.api", "com.settlement.core"})
public class SettlementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettlementApiApplication.class, args);
    }
}
