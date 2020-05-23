package com.maomao;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableHelloWorld
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String hw = context.getBean("helloWorld", String.class);
        System.out.println(hw);
        System.out.println(hw.hashCode());

        String hw1 = context.getBean("helloWorld", String.class);
        System.out.println(hw1);
        System.out.println(hw1.hashCode());

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("..." + name);
        }

        System.out.println(System.getProperty("user.name"));
    }
}
