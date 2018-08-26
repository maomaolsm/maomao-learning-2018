package com.maomao.spring2.beans;

import com.maomao.spring2.core.FactoryBean;

/**
 * Created by maomao on 2018/8/20.
 */
public class BeanWrapper extends FactoryBean {

    // 还会用到 观察者 模式
    // 1、支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;

    // 原始的通过反射 new 出来的，要把包装起来，存下来
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.originalInstance = instance;
    }

    public Object getWrapperInstance() {
        return this.wrapperInstance;
    }

    // 返回代理以后的 class
    // 可能会是这个 $proxy0
    public Class<?> getWrapperClass() {
        return this.getWrapperInstance().getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
