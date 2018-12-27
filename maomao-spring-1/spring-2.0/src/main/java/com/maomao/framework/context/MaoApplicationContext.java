package com.maomao.framework.context;

import com.maomao.framework.core.BeanFactory;

/**
 * Created by maomao on 2018/12/27.
 */
public class MaoApplicationContext implements BeanFactory {

    private String[] configLocations;

    public MaoApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public void refresh() {

        // 定位

        // 加载

        // 注册

        // 依赖注入（lazy-init = false），要执行依赖注入
        // 在这里自动调用 getBean 方法

    }

    public Object getBean(String name) {
        return null;
    }
}
