package com.maomao;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 应用事件引导类
 *
 * @author senmao.li
 * @since 2018/8/21 17:15
 */
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {
        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册应用事件监听器
        context.addApplicationListener(event ->
                System.out.println("--------------监听到事件 ：" + event));

        // 启动上下文
        context.refresh();

        // 多了两个 PayloadApplicationEvent 事件
        context.publishEvent("HelloWorld");
        context.publishEvent("HelloWorld");

        // 发送自定义事件
        context.publishEvent(new ApplicationEvent("maomao") {});

        // 关闭上下文
        context.close();
    }
}
