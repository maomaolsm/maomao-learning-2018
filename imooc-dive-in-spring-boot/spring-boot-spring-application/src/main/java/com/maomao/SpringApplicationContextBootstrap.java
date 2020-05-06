package com.maomao;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/22 16:53
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                // 强制转换类型，spring 中是通过 createApplicationContext 方法进行判断加载的
                // 不同的 web 类型，ConfigurableApplicationContext 的类型也不一样
                // 不同的 web 类型，Environment 的类型也不一样
                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println("-----ConfigurableApplicationContext 类型 : "
                + context.getClass().getName());
        System.out.println("-----Environment 类型 : "
                + context.getEnvironment().getClass().getName());

        // 关闭上下文
        context.close();

    }
}
