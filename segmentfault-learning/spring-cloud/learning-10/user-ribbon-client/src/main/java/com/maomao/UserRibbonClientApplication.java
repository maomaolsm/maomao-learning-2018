package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/12 15:45
 */
@SpringBootApplication
@RibbonClient("user-service-provider")
public class UserRibbonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRibbonClientApplication.class, args);
    }

}
