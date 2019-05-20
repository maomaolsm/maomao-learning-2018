package com.maomao.framework.context;

import com.maomao.framework.beans.BeanDefinition;
import com.maomao.framework.beans.BeanDefinitionReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maomao on 2019/1/5.
 */
public class ApplicationContext {

    private String configLocation;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public ApplicationContext(String contextConfigLocation) {
        this.configLocation = contextConfigLocation;
        refresh();
    }

    private void refresh() {

        // 定位
        this.reader = new BeanDefinitionReader(configLocation);

        // 加载
        // 注册
        doRegistry();

        // 依赖注入
        doAutowired();

    }

    private void doAutowired() {

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();

            getBean(beanName);

        }

    }

    public Object getBean(String beanName) {
        return null;
    }

    private void doRegistry() {

    }


    public int getBeanDefinitionCount() {
        return 0;
    }

    public String[] getBeanDefinitionNames() {
        return null;
    }
}
