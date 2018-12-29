package com.maomao.framework.context;

import com.maomao.framework.beans.BeanDefinition;
import com.maomao.framework.context.support.BeanDefinitionReader;
import com.maomao.framework.core.BeanFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/12/27.
 */
public class MaoApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public MaoApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public void refresh() {

        // 定位
        try {
            this.reader = new BeanDefinitionReader(configLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();


        // 注册
        doRegistry(beanDefinitions);

        // 依赖注入（lazy-init = false），要执行依赖注入
        // 在这里自动调用 getBean 方法

    }

    // 真正的将 beanDefinitions 注册到 beanDefinitionMap（ioc） 中
    // beanName 有三种情况
    // 1、默认是类名首字母小写
    // 2、自定义名称
    // 3、接口注入
    private void doRegistry(List<String> beanDefinitions) {

        try {

            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);

                // 用他的实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }

                BeanDefinition beanDefinition = reader.registerBean(className);

                if (beanDefinition != null) {
                    beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> clazz : interfaces) {

                    // 如果多个 spring 会报错
                    this.beanDefinitionMap.put(clazz.getName(),beanDefinition);
                }

                // 到这里，容器初始化完毕

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 依赖注入，从这里开始，通过读取 beanDefinition 中的信息
    // 然后，通过反射机制创建一个实例并返回
    // spring 的做法是，不会把最原始的对象放出去，会用一个 beanWrapper 来进行一次包装
    // 包装器模式：
    // 1、保留原来的 oop 关系
    // 2、我需要对他进行扩展，增强（为了以后 aop 打基础）
    public Object getBean(String name) {
        return null;
    }
}
