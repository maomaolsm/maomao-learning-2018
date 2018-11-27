package com.maomao.bootstrap;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 {@link PropertySourceLocator} 实现
 * Created by maomao on 2018/11/26.
 */
public class MyPropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {

            ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);

            // 获取 propertySources
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            // 定义新的 propertySource, 并且放置在首位
            propertySources.addFirst(createPropertySource());

        }
        return null;
    }

    private PropertySource createPropertySource() {

        Map<String, Object> source = new HashMap<>();

        source.put("spring.application.name", "茂茂的 spring cloud 程序");
        // 设置名称和来源
        PropertySource propertySource = new MapPropertySource("over-bootstrap-property-source", source);

        return propertySource;
    }
}