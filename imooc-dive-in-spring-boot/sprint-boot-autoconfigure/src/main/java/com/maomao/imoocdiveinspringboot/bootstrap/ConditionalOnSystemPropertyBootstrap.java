package com.maomao.imoocdiveinspringboot.bootstrap;

import com.maomao.imoocdiveinspringboot.condition.ConditionOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 系统属性条件引导类
 *
 * 基于编程方法实现
 *
 * @author senmao.li
 * @since 2018/8/14 11:58
 */
public class ConditionalOnSystemPropertyBootstrap {


    @Bean
    @ConditionOnSystemProperty(name = "user.name", value = "dell")
    public String helloWorld() {
        return "Hello World maomao!";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 通过名称和类型获取 helloWorld Bean
        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println("helloWorld Bean : " + helloWorld);

        // 关闭上下文
        context.close();
    }
}
