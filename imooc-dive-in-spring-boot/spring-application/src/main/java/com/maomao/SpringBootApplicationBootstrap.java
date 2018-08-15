package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link SpringApplication} 引导类
 */
public class SpringBootApplicationBootstrap {

    public static void main(String[] args) {
//		SpringApplication.run(ApplicationConfiguration.class, args);

        Set<String> sources = new HashSet<>();
        // 配置 class 名称
        sources.add(ApplicationConfiguration.class.getName());

        SpringApplication application = new SpringApplication();
        application.setSources(sources);

        ConfigurableApplicationContext context = application.run(args);
        // 证明 ApplicationConfiguration 是一个 Bean
//        System.out.println("Bean : " + context.getBean(ApplicationConfiguration.class));
    }

    @SpringBootApplication
    public static class ApplicationConfiguration {

    }
}
