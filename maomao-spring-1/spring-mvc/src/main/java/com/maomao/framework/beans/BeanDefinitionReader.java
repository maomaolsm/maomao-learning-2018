package com.maomao.framework.beans;

import java.io.InputStream;
import java.util.Properties;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/1/8 16:51
 */
public class BeanDefinitionReader {

    private Properties properties;

    public BeanDefinitionReader(String configLocation) {
        InputStream is = this.getClass().getClassLoader()
                .getResourceAsStream(configLocation);



    }
}
