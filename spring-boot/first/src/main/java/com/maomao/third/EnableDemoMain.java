package com.maomao.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/9 20:38
 */
@SpringBootApplication
@EnableDefineService
public class EnableDemoMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ca = SpringApplication.run(EnableDemoMain.class, args);

        System.out.println(ca.getBean(CacheService.class));
    }
}
