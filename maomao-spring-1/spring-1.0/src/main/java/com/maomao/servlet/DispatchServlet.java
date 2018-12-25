package com.maomao.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private List<String> beans = new ArrayList<String>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 开始初始化

        // 定位
        doLoadConfig();

        // 加载
        doScanner();

        // 注册
        doRegistry();

        // 自动依赖注入
        doAutowired();

        // 如果是 springmvc 会多一个 handlerMapping

        // 将 @requestMapping 中配置的 url 和一个 method 关联上
        // 以便于从浏览器获得用户输入的 url 以后，能够找到具体执行的 method 通过反射去调用
        initHandlerMapping();

    }

    private void initHandlerMapping() {

    }

    private void doAutowired() {

    }

    private void doRegistry() {

    }

    private void doScanner() {

    }

    private void doLoadConfig() {

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
