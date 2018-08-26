package com.maomao.spring2.framework.core;

/**
 * Created by maomao on 2018/8/20.
 */
public interface BeanFactory {
    /**
     * 根据 beanName 从 ioc 容器中获得一个实例 bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
