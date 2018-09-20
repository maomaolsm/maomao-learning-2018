package com.maomao.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引导类
 *
 * @author senmao.li
 * @since 2018/9/20 15:35
 */
@SpringBootApplication(scanBasePackages = "com.maomao")
public class SpringBootWebMvcBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcBootstrap.class, args);
    }
}
