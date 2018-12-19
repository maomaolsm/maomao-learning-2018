package com.maomao;

import com.maomao.api.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by maomao on 2018/12/19.
 */
@SpringBootApplication
@EnableEurekaClient
// 声明 UserService 接口作为 feign client 调用
@EnableFeignClients(clients = UserService.class)
public class UserServiceClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }
}
