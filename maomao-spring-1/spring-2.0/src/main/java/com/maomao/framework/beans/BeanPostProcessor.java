package com.maomao.framework.beans;

/**
 * 用作事件监听的
 *
 * Created by maomao on 2018/12/30.
 */
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
