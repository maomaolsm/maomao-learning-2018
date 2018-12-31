package com.maomao.framework.core;

/**
 * Created by maomao on 2018/12/27.
 */
public interface BeanFactory {

    /**
     * 从 ioc 中获取一个实例 Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

}
