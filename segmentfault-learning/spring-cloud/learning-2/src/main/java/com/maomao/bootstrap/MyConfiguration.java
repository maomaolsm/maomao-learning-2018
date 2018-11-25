package com.maomao.bootstrap;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Bootstrap 配置 Bean
 * Created by maomao on 2018/11/24.
 */
@Configuration
public class MyConfiguration implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        // 从 ConfigurableApplicationContext 获取 ConfigurableEnvironment 实例
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        // 获取 propertySources
        MutablePropertySources propertySources = environment.getPropertySources();
        // 定义新的 propertySource, 并且放置在首位
        propertySources.addFirst(createPropertySource());
    }

    private PropertySource createPropertySource() {

        Map<String, Object> source = new HashMap<>();

        source.put("name", "茂茂");

        PropertySource propertySource = new MapPropertySource("my-property-source", source);

        return propertySource;
    }
}
