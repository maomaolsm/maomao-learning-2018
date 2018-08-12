package com.maomao.annotationdrivendevelopment.bootstrap;

import com.maomao.annotationdrivendevelopment.config.UserConfiguration;
import com.maomao.annotationdrivendevelopment.domian.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by maomao on 2018/8/11.
 */
public class AnnotationConfigBootstrap {
    public static void main(String[] args) {

        // 构建一个上下文
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext();

        // 需要注册一个 UserConfiguration 的 Bean
        applicationContext.register(UserConfiguration.class);

        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user.getName());
    }
}
