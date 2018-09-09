package com.maomao.springaop.framework.beans;

/**
 * 用来做事件监听
 *
 * Created by maomao on 2018/8/25.
 */
public class MaoBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
