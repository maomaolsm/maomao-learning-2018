package com.maomao.framework.webmvc.servlet;

import com.maomao.framework.ApplicationContext;
import com.maomao.framework.webmvc.HandlerAdapter;
import com.maomao.framework.webmvc.HandlerMapping;
import com.maomao.framework.webmvc.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maomao on 2019/1/5.
 */
public class DispatcherServlet extends HttpServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
//        HandlerMapping handlerMapping = handlerMappingMap.get(url);

//        try {
//            ModelAndView mv = (ModelAndView) handlerMapping.getMethod().invoke(handlerMapping.getController());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

        HandlerMapping handler = getHandler(req);
        HandlerAdapter ha = getHandlerAdapter(handler);

        ModelAndView mv = ha.handle(req, resp, handler);

        processDispatchResult(resp, mv);

    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) {

    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        return null;
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 相当于把 ioc 容器初始化
        ApplicationContext context = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        initStrategies(context);

    }

    private void initStrategies(ApplicationContext context) {

        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);

        // 将请求映射到处理器
        initHandlerMappings(context);

        // 进行多类型的参数动态匹配
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);

        // 解析逻辑视图到具体的视图实现
        initViewResolvers(context);
        initFlashMapManager(context);

    }

    private void initFlashMapManager(ApplicationContext context) {

    }

    private void initViewResolvers(ApplicationContext context) {

    }

    private void initRequestToViewNameTranslator(ApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(ApplicationContext context) {

    }

    private void initHandlerAdapters(ApplicationContext context) {

    }

    private void initHandlerMappings(ApplicationContext context) {



    }

    private void initThemeResolver(ApplicationContext context) {

    }

    private void initLocaleResolver(ApplicationContext context) {
    }

    private void initMultipartResolver(ApplicationContext context) {

    }
}
