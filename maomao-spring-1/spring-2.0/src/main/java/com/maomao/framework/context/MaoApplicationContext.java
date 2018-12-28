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
    private void doRegistry(List<String> beanDefinitions) {
        
    }

    public Object getBean(String name) {
        return null;
    }
}
