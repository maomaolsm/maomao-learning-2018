package com.maomao.init;

import com.maomao.config.ApplicationConfig;
import com.maomao.config.WebConfig;
import com.maomao.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    // spring 容器,相当于加载 applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                ApplicationConfig.class,
                WebSecurityConfig.class};
    }

    // servletContext,相当于加载springmvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
