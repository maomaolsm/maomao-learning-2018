package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/11/28 18:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClient.class, args);
    }
}
