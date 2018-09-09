package com.maomao.springaop.framework.context;

import com.maomao.springaop.framework.beans.MaoBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/9/6.
 */
public class MaoDefaultListableBeanFactory extends MaoAbstractApplicationContext {

    // 用来保存配置信息
    protected Map<String, MaoBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, MaoBeanDefinition>();


    protected void onRefresh()  {

    }

    @Override
    protected void refreshBeanFactory() {

    }
}
