package com.maomao.framework.beans;

/**
 * 用来储存配置文件中的信息
 * 相当于保存在内存中的配置
 * <p>
 * Created by maomao on 2018/12/27.
 */
public class BeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    // bean 在 factory 中的一个名字，在 ioc 中的一个名字
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public boolean isNotLazyInit() {
        return !lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
