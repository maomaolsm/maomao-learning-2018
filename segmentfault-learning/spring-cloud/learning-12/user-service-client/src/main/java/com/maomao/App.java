package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 引导类
 *
 * @author senmao.li
 * @since 2018/11/22 10:32
 */
@SpringBootApplication
@RibbonClient("user-service-provider") // 指定目标应用名称
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
