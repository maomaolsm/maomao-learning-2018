package com.maomao.springaop.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Ĭ�Ͼ��� JDK ��̬����
 * Created by maomao on 2018/9/6.
 */
public class MaoAopProxy implements InvocationHandler {

    private MaoAopConfig config;

    private Object target;

    // ��ԭ���Ķ��󴫽���
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

        // ��ԭʼ��������֮ǰҪִ����ǿ�Ĵ���
        if(config.contains(method)){
            MaoAopConfig.MaoAspect aspect = config.get(method);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }
        // �������ԭʼ����
        Object obj = method.invoke(this.target,args);

        // ��ԭʼ��������֮��Ҫִ����ǿ�Ĵ���
        if(config.contains(method)){
            MaoAopConfig.MaoAspect aspect = config.get(method);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }

        // ����ԭʼ�ķ���ֵ���س�ȥ
        return obj;
    }
}
