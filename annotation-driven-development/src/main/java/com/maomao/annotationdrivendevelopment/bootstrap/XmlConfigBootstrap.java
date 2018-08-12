package com.maomao.annotationdrivendevelopment.bootstrap;

import com.maomao.annotationdrivendevelopment.domian.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by maomao on 2018/8/11.
 */
public class XmlConfigBootstrap {
    public static void main(String[] args) {

        // 构建一个上下文
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext();

        applicationContext.setConfigLocations(
            "classpath:/META-INF/spring/context.xml");
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user.getName());
    }
}
