package com.maomao.springmvc.framework.context.support;

import com.maomao.springmvc.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取、解析
 * <p>
 * Created by maomao on 2018/8/20.
 */
public class BeanDefinitionReader {

    private Properties contextConfig = new Properties();

    private List<String> registerBeanClasses = new ArrayList<String>();

    // 在配置文件中，用来获取自动扫描的包的 key
    private final String SCAN_PACKAGE = "scanPackage";

    // 定位
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
        doScanner(contextConfig.getProperty(SCAN_PACKAGE));
    }

    public List<String> loadBeanDefinitions() {
        return this.registerBeanClasses;
    }

    // 每注册一个 className, 就返回一个 BeanDefinition, 我们自己包装的
    // 只是为了对配置信息进行一个包装
    public BeanDefinition registerBean(String className) {
        if (this.registerBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(
                lowerFirstCase(className.substring(className.lastIndexOf(".") + 1))
            );
            return beanDefinition;
        }

        return null;
    }

    // 加载
    // 递归扫描所有的相关联的 class ，并保存到一个 list 中
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                registerBeanClasses.add(packageName + "." + file.getName().replace(".class", ""));
            }

        }
    }

    public Properties getContextConfig() {
        return this.contextConfig;
    }

    // 首字母转化为小写
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
