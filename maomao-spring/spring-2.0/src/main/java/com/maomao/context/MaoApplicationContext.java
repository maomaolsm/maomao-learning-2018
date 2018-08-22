package com.maomao.context;

import com.maomao.beans.BeanDefinition;
import com.maomao.context.support.BeanDefinitionReader;
import com.maomao.core.BeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/8/20.
 */
public class MaoApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> BeanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public MaoApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public void refresh() {

        // 定位
        this.reader = new BeanDefinitionReader(configLocations);

        // 加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        // 注册
        doRegistry(beanDefinitions);

        // 依赖注入（lazy-init = false）, 要执行依赖注入
        // 在这里自动调用 getBean 方法

    }

    // 真正的将 BeanDefinitions 注册到 BeanDefinitionMap（IOC 容器）中
    private void doRegistry(List<String> beanDefinitions) {

        // beanName 有三种情况：
        // 1、默认是类名首字母小写
        // 2、自定义名字
        // 3、接口注入

        try {
            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);

                // 如果是一个接口是不能实例化的
                // 用他的实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }

                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
                    this.BeanDefinitionMap.put(
                        beanDefinition.getFactoryBeanName(), beanDefinition
                    );
                }

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> clazz : interfaces) {
                    // 如果是多个实现类，只能覆盖
                    // 为什么？因为 spring 没那么智能，就是这么傻
                    // 怎么办？这个时候，可以自定义名字
                    this.BeanDefinitionMap.put(clazz.getName(), beanDefinition);
                }
                // 到这里为止，容器初始化完毕
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 依赖注入，从这里开始，通过获取 BeanDefinition 中的信息
    // 然后通过反射创建一个实例并返回
    // spring 做法是，不会把最原始的对象放出去，会用一个 BeanWrapper 来进行一次包装
    // 装饰器模式：
    // 1、保留原来的 oop 关系
    // 2、我需要对他进行扩展，增强（为了以后 aop 打基础）
    @Override
    public Object getBean(String name) {
        return null;
    }
}
