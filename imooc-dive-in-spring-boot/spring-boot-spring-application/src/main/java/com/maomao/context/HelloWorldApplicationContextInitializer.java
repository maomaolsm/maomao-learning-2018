package com.maomao.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/21 15:15
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext>
        implements ApplicationContextInitializer<C> {
    @Override
    public void initialize(C applicationContext) {
        System.out.println("-------------------ConfigurableApplicationContext.id = " + applicationContext.getId());
    }
}
