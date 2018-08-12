package com.maomao.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> beanNames = new ArrayList<String>();

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
        //开始初始化的进程


        //定位
        doLoadConfig(config.getInitParameter("contentConfigLocation"));


        //加载
        doScanner();


        //注册
        doRegistry();


        //自动依赖注入
        doAutowired();


        //如果是springMVC，会多设计一个HandleMapping


        //将@RequestMapping中配置的url和method通过反射去调用
        //以便于从浏览器获得用户输入的url以后，能够找到具体执行的Method通过反射去调用
        initHandleMapping();
    }

    private void initHandleMapping() {

    }

    private void doRegistry() {

    }

    private void doAutowired() {

    }

    private void doScanner() {

    }

    private void doLoadConfig(String location) {
        //在spring中是通过Reader去查找和定位的
        InputStream is = this.getClass()
            .getClassLoader()
            .getResourceAsStream(location.replace("classpath:",""));

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
}