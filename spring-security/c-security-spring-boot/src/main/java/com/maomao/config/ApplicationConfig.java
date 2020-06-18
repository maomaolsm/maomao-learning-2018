package com.maomao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 相当于applicationContext.xml
 */
@Configuration
@ComponentScan(basePackages = "com.maomao"
        , excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class ApplicationConfig {
    // 除controller的其他bean，如：数据库连接池，事务管理器，业务bean等

}
