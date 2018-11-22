package com.maomao.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 事件/监听器 demo
 *
 * @author senmao.li
 * @since 2018/10/11 14:29
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 添加监听
        context.addApplicationListener(new MyApplicationListener());

        // 上下文启动
        context.refresh();

        // 发布事件
        context.publishEvent(new MyApplicationEvent("hello, word !"));
    }

    private static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
            System.out.println();
        }
    }


    private static class MyApplicationEvent extends ApplicationEvent {

        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
