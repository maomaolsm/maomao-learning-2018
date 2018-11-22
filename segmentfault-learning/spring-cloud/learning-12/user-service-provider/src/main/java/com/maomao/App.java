package com.maomao;

import com.maomao.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 引导类
 *
 * @author senmao.li
 * @since 2018/11/22 14:57
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient // 激活服务发现客户端
@EnableBinding(UserMessage.class) // 激活 stream binding 到 userMessage
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
