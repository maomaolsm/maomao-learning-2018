package com.maomao.framework.context;

import com.maomao.demo.DemoAction;
import com.maomao.framework.annotation.Autowired;
import com.maomao.framework.annotation.Controller;
import com.maomao.framework.annotation.Service;
import com.maomao.framework.beans.BeanDefinition;
import com.maomao.framework.beans.BeanPostProcessor;
import com.maomao.framework.beans.BeanWrapper;
import com.maomao.framework.context.support.BeanDefinitionReader;
import com.maomao.framework.core.BeanFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/12/27.
 */
public class MaoApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    // 用来保存配置信息
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    // 用来保证注册式单例
    private Map<String, Object> beanCacheMap = new HashMap<String, Object>();

    // 存储被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();


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
        doAutowired();

        DemoAction demoAction = (DemoAction) getBean("demoAction");
        demoAction.query();

    }

    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {

            String beanName = entry.getKey();

            if (entry.getValue().isNotLazyInit()) {
                getBean(beanName);
            }
        }

        // todo 这里不能用两次循环解决注入时未创建实例的问题，可能需要用到递归
        for (Map.Entry<String, BeanWrapper> entry : beanWrapperMap.entrySet()) {
            populateBean(entry.getKey(), entry.getValue().getWrapperInstance());
        }
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
                    this.beanDefinitionMap.put(clazz.getName(), beanDefinition);
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
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        String className = beanDefinition.getBeanClassName();

        try {

            // 生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            Object instance = instantiationBean(beanDefinition);
            if (null == instance) {
                return null;
            }

            // 在实例初始化之前调用
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);

            beanWrapper.setBeanPostProcessor(beanPostProcessor);

            beanWrapperMap.put(beanName, beanWrapper);

            // 在实例初始化之后调用
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);

//            populateBean(beanName, instance);

            // 这里，我们有一定的可操作的空间
            return beanWrapperMap.get(beanName).getWrapperInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object instantiationBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();

        try {
            if (this.beanCacheMap.containsKey(className)) {
                instance = beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void populateBean(String beanName, Object instance) {

        Class<?> clazz = instance.getClass();

        if (clazz.isAnnotationPresent(Controller.class)
            || clazz.isAnnotationPresent(Service.class)) {

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredBeanName = autowired.value().trim();
                    if ("".equals(autowiredBeanName)) {
                        autowiredBeanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    try {

                        System.out.println("---------------" + instance
                            + "---------------" + autowiredBeanName
                            + "---------------" + beanWrapperMap.get(autowiredBeanName));

                        // set(Object obj, Object value) ： 向obj对象的这个Field设置新值value
                        field.set(instance,
                            beanWrapperMap.get(autowiredBeanName).getWrapperInstance());

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
