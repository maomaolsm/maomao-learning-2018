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
public class AfterHelloWorldApplicationContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("-------------------After Application.id = " + applicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
