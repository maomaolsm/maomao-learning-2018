package com.maomao.springaop.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 默认就用 JDK 动态代理
 * Created by maomao on 2018/9/6.
 */
public class MaoAopProxy implements InvocationHandler {

    private MaoAopConfig config;

    private Object target;

    // 把原生的对象传进来
    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public void setConfig(MaoAopConfig config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在原始方法调用之前要执行增强的代码
        if(config.contains(method)){
            MaoAopConfig.MaoAspect aspect = config.get(method);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }
        // 反射调用原始方法
        Object obj = method.invoke(this.target,args);

        // 在原始方法调用之后要执行增强的代码
        if(config.contains(method)){
            MaoAopConfig.MaoAspect aspect = config.get(method);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }

        // 将最原始的返回值返回出去
        return obj;
    }
}
