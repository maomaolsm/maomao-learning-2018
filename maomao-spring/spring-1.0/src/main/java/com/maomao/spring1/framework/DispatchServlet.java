package com.maomao.spring1.framework;

import com.maomao.spring1.demo.action.DemoAction;
import com.maomao.spring1.framework.annotation.Autowired;
import com.maomao.spring1.framework.annotation.Controller;
import com.maomao.spring1.framework.annotation.Service;

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
 * Created by maomao on 2018/5/1.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    // 先声明一个容器
    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用dopost");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 开始初始化的进程


        // 定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));


        // 加载
        doScanner(contextConfig.getProperty("scanPackage"));


        // 注册
        doRegistry();


        // 自动依赖注入
        // 在 spring 中是通过 getBean 方法才触发依赖注入的
        doAutowired();

        // 验证 service 是否被注入
        DemoAction demoAction = (DemoAction) beanMap.get("demoAction");
        demoAction.query(null, null, "maomao");

        // 如果是springMVC，会多设计一个HandleMapping


        // 将 @RequestMapping 中配置的 url 和 method 通过反射去调用
        // 以便于从浏览器获得用户输入的 url 以后，能够找到具体执行的 Method 通过反射去调用
        initHandleMapping();
    }

    private void initHandleMapping() {

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

                    // 在 spring 中这个阶段是不会直接 put instance , 这里 put 的是 BeanDefinition
                    beanMap.put(lowerFirstCase(clazz.getSimpleName()), clazz.newInstance());

                } else if (clazz.isAnnotationPresent(Service.class)) {

                    Service service = clazz.getAnnotation(Service.class);

                    // 默认用类名首字母注入
                    // 如果自己定义了 beanName ，那么优先使用自己定义的 beanName
                    // 如果是一个接口，使用接口的类型去自动注入

                    // 在 spring 中同样会分别调用不同的方法 autowiredByName autowiredByType

                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getCanonicalName());
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

    private void doAutowired() {

        if (beanMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entity : beanMap.entrySet()) {

            Field[] fields = entity.getValue().getClass().getDeclaredFields();

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
                    field.set(entity.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", ""));
            }

        }
    }

    private void doLoadConfig(String location) {

        // 在 spring 中是通过 Reader 去查找和定位的
        InputStream is = this.getClass()
            .getClassLoader()
            .getResourceAsStream(location.replace("classpath:", ""));

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

    // 首字母转化为小写
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}