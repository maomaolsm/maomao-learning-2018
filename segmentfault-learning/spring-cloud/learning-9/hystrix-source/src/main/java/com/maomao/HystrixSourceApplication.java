package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Created by maomao on 2018/12/7.
 */
@SpringBootApplication
@EnableCircuitBreaker
public class HystrixSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixSourceApplication.class, args);
    }
}
