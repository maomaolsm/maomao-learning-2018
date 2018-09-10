package com.maomao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * spring web mvc 配置
 * <p>
 * 等同于 app-context.xml 文件中注释掉的配置
 *
 * @author senmao.li
 * @since 2018/9/7 17:46
 */
@Configuration
@EnableWebMvc
public class WebConfig {

    //    <!-- configure the InternalResourceViewResolver -->
//    <bean id="viewResolver"
//    class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
//        <!-- 前缀 -->
//        <property name="prefix" value="/WEB-INF/jsp/"/>
//        <!-- 后缀 -->
//        <property name="suffix" value=".jsp"/>
//    </bean>
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
