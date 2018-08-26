package com.maomao.spring2.context;

import com.maomao.demo.mvc.action.DemoAction;
import com.maomao.spring2.annotation.Autowired;
import com.maomao.spring2.annotation.Controller;
import com.maomao.spring2.annotation.Service;
import com.maomao.spring2.beans.BeanDefinition;
import com.maomao.spring2.beans.BeanPostProcessor;
import com.maomao.spring2.beans.BeanWrapper;
import com.maomao.spring2.context.support.BeanDefinitionReader;
import com.maomao.spring2.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/8/20.
 */
public class MaoApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    // 用来保存配置信息
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    // 用来保证注册式单例的容器
    private Map<String, Object> beanCacheMap = new HashMap<String, Object>();

    // 用来存储所有被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();

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
        doAutowired();

        DemoAction demoAction = (DemoAction) this.getBean("demoAction");
        demoAction.query(null, null, "test maomao");

    }

    // 开始执行自动化的依赖注入
    private void doAutowired() {

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();

            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }

        for (Map.Entry<String, BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()) {
            populateBean(beanWrapperEntry.getKey(), beanWrapperEntry.getValue());
        }
    }

    public void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();

        // 不是所有的牛奶都叫特仑苏
        if (!(clazz.isAnnotationPresent(Controller.class)
            || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        Field[] fields = clazz.getFields();
        for (Field field : fields) {

            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();

            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);

        }
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
                    this.beanDefinitionMap.put(
                        beanDefinition.getFactoryBeanName(), beanDefinition
                    );
                }

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> clazz : interfaces) {
                    // 如果是多个实现类，只能覆盖
                    // 为什么？因为 spring 没那么智能，就是这么傻
                    // 怎么办？这个时候，可以自定义名字
                    this.beanDefinitionMap.put(clazz.getName(), beanDefinition);
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
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();
        try {

            // 生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();


            Object instance = instantiationBean(beanDefinition);
            if (null == instance) {
                return null;
            }

            // 在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName, beanWrapper);

            // 在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);

            // 这里会有问题，被注入的对象有可能没有实例化
//            populateBean(beanName, instance);

            // 通过这样一调用，相当于给我们自己留有了可操作的空间
            return this.beanWrapperMap.get(beanName).getWrapperInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 传一个 beanDefinition，返回一个实例 bean
    private Object instantiationBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {

            // 因为根据 class 才能确定一个类是否有实例
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
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
}
