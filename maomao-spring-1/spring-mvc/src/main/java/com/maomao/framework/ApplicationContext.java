package com.maomao.framework;

/**
 * Created by maomao on 2019/1/5.
 */
public class ApplicationContext {

    private String contextConfigLocation;

    public ApplicationContext(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
        refresh();
    }

    private void refresh() {

        // 定位
        // 加载
        // 注册
        doRegistry();

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
