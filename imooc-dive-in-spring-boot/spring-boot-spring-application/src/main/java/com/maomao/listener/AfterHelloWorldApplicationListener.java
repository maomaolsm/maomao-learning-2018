package com.maomao.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * 监听 ContextRefreshedEvent
 *
 * @author senmao.li
 * @since 2018/8/21 16:42
 */
public class AfterHelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("-----------AfterHelloWorld : " + event.getApplicationContext().getId()
                + " , timestamp : " + event.getTimestamp());
    }

    @Override
    public int getOrder() {
        // 证明 order 的作用
        return Ordered.LOWEST_PRECEDENCE;
    }
}