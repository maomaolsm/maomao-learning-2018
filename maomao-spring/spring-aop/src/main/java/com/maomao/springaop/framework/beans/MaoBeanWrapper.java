package com.maomao.springaop.framework.beans;

import com.maomao.springaop.framework.aop.MaoAopProxy;
import com.maomao.springaop.framework.core.MaoFactoryBean;

/**
 * Created by maomao on 2018/8/20.
 */
public class MaoBeanWrapper extends MaoFactoryBean {

    private MaoAopProxy aopProxy = new MaoAopProxy();

    // 还会用到 观察者 模式
    // 1、支持事件响应，会有一个监听
    private MaoBeanPostProcessor postProcessor;

    private Object wrapperInstance;

    // 原始的通过反射 new 出来的，要把包装起来，存下来
    private Object originalInstance;

    public MaoBeanWrapper(Object instance) {

        // 从这里开始，我们要把动态代理的代码添加进来了
        this.wrapperInstance = aopProxy.getProxy(instance);
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

    public MaoBeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(MaoBeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
