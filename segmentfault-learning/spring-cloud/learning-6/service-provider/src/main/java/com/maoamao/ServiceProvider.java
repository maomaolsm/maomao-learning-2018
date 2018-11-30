package com.maoamao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/11/29 15:01
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceProvider {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider.class, args);
    }
}
