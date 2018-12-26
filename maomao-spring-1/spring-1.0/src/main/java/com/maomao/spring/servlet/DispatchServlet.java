package com.maomao.spring.servlet;

import com.maomao.demo.DemoAction;
import com.maomao.spring.annotation.Autowired;
import com.maomao.spring.annotation.Controller;
import com.maomao.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/12/24.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 开始初始化

        // 定位
        try {
            doLoadConfig(config.getInitParameter("contextConfigLocation"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载
        doScanner(contextConfig.getProperty("scanPackage"));

        // 注册
        doRegistry();

        // 自动依赖注入
        // spring 中是通过调用 getBean 方法才触发依赖注入的
        doAutowired();

        DemoAction action = (DemoAction) beanMap.get("demoAction");
        action.query(null, null, "maomao");

        // 如果是 springmvc 会多一个 handlerMapping

        // 将 @requestMapping 中配置的 url 和一个 method 关联上
        // 以便于从浏览器获得用户输入的 url 以后，能够找到具体执行的 method 通过反射去调用
        initHandlerMapping();

    }

    private void initHandlerMapping() {

    }

    private void doAutowired() {

        if (beanMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {

            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }

                Autowired autowired = field.getAnnotation(Autowired.class);

                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private void doRegistry() {

        if (classNames.isEmpty()) {
            return;
        }

        try {

            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                // 在 spring 中用的多个子方法来处理的
                // parseArray, parseMap
                if (clazz.isAnnotationPresent(Controller.class)) {

                    String beanName = lowerFirstCase(clazz.getSimpleName());

                    // 在 spring 中在这个阶段是不会直接 put instance
                    // 这里 put 的是 BeanDefinition
                    beanMap.put(beanName, clazz.newInstance());

                } else if (clazz.isAnnotationPresent(Service.class)) {

                    Service service = clazz.getAnnotation(Service.class);

                    // 默认用类名首字母注入
                    // 如果自己定义了 beanName，那么优先使用自己定义的 beanName
                    // 如果是一个接口，实用接口类型去自动注入

                    // 在 spring 中同样会分别调用不同的方法 autowiredByName autowiredByType

                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    beanMap.put(beanName, instance);

                    Class<?>[] interfaces = clazz.getInterfaces();

                    for (Class<?> i : interfaces) {
                        beanMap.put(i.getName(), instance);
                    }

                } else {
                    continue;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doScanner(String packageName) {

        URL url = this.getClass()
                .getClassLoader()
                .getResource("/"
                        + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName
                        + "."
                        + file.getName().replace(".class", ""));
            }
        }
    }

    private void doLoadConfig(String location) throws IOException {

        // 在 spring 中是通过 reader 去查找和定位
        InputStream is = this.getClass()
                .getClassLoader()
                .getResourceAsStream(location.replace("classpath:", ""));

        try {
            contextConfig.load(is);
        } finally {
            if (null != is) {
                is.close();
            }
        }

    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------- 我是 doPost ------------");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
