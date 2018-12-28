package com.maomao.framework.context.support;

import com.maomao.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取和解析
 * <p>
 * Created by maomao on 2018/12/27.
 */
public class BeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registerBeanClasses = new ArrayList<String>();

    public BeanDefinitionReader(String... locations) throws IOException {

        InputStream is = this.getClass()
            .getClassLoader()
            .getResourceAsStream(locations[0].replace("classpath:", ""));

        try {
            config.load(is);
        } finally {
            if (null != is) {
                is.close();
            }
        }

        doScanner(config.getProperty("scanPackage"));

    }

    // 递归扫描所有的相关联的 class，保存到一个 list 中
    private void doScanner(String packageName) {

        URL url = this.getClass()
            .getClassLoader()
            .getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                registerBeanClasses.add(packageName + "."
                    + file.getName().replace(".class", ""));
            }
        }
    }

    public List<String> loadBeanDefinitions() {
        return this.registerBeanClasses;
    }

    // 每注册一个 className，就返回一个 beanDefinition，我自己包装
    // 只是为了对配置信息进行一个包装
    public BeanDefinition registerBean(String className) {
        if (this.registerBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);

            // 这里默认首字母小写
            beanDefinition.setFactoryBeanName(
                lowerFirstCase(
                    className.substring(
                        className.lastIndexOf(".") + 1)
                ));
            return beanDefinition;
        }
        return null;
    }

    public Properties getConfig() {
        return this.config;
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
