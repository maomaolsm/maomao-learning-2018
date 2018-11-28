package com.maomao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * Created by maomao on 2018/11/27.
 */
@SpringBootApplication
public class App {

    private final ContextRefresher contextRefresher;

    @Autowired
    public App(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @Scheduled(fixedRate = 1000L)
    public void update() {

        // 定时一秒刷新一下配置，实现修改配置远程文件后自动更新
        Set<String> keys = contextRefresher.refresh();

        if (!keys.isEmpty()) {
            System.out.println("本次更新的配置项：" + keys);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
