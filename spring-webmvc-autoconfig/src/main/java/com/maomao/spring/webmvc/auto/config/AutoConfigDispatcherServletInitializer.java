package com.maomao.spring.webmvc.auto.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by maomao on 2018/8/12.
 */
public class AutoConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            SpringWebMvcConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
            "/*"
        };
    }
}
