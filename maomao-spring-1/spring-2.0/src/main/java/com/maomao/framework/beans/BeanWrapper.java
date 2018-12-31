package com.maomao.framework.beans;

import com.maomao.framework.core.FactoryBean;

/**
 * Created by maomao on 2018/12/27.
 */
public class BeanWrapper extends FactoryBean{

    // 用到观察者模式
    // 1、支持事件响应，会有一个监听
    private BeanPostProcessor beanPostProcessor;

    private Object wrapperInstance;

    private Object originalInstance;

    public BeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.originalInstance = instance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    // 返回代理以后的 class
    // 可能会是这个 $proxy0
    public Class<?> getWapperdClass() {
        return this.getWapperdClass().getClass();
    }

    public BeanPostProcessor getBeanPostProcessor() {
        return beanPostProcessor;
    }

    public void setBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessor = beanPostProcessor;
    }
}
