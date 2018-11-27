package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by maomao on 2018/11/26.
 */
@SpringBootApplication
@EnableConfigServer
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        System.out.println("------------------" + System.getProperty("user.dir"));

    }
}
