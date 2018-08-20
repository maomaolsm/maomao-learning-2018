package com.maomao.context.support;

import com.maomao.beans.BeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取、解析
 *
 * Created by maomao on 2018/8/20.
 */
public class BeanDefinitionReader {

    private Properties contextConfig = new Properties();

    public BeanDefinitionReader(String... locations) {

        // 在 spring 中是通过 Reader 去查找和定位的
        InputStream is = this.getClass()
            .getClassLoader()
            .getResourceAsStream(locations[0].replace("classpath:", ""));

        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public BeanDefinition registerBean(String className) {
        return null;
    }


}
