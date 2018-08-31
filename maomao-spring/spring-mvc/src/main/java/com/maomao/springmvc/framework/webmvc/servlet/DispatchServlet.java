package com.maomao.springmvc.framework.webmvc.servlet;

import com.maomao.springmvc.framework.context.MaoApplicationContext;
import com.maomao.springmvc.framework.webmvc.HandlerAdapter;
import com.maomao.springmvc.framework.webmvc.HandlerMapping;
import com.maomao.springmvc.framework.webmvc.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * servlet 只是作为一个 MVC 的启动入口
 * <p>
 * Created by maomao on 2018/8/19.
 */
public class DispatchServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

//    private Map<String, HandlerMapping> handlerMapping = new HashMap<String, HandlerMapping>();

    // 课后思考一下这样设计的经典之处 为什么是 list
    // HandlerMapping 是 spring 中最核心的设计，也是最经典的
    // 他牛逼到直接干掉了其他的 mvc 框架
    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        url = url.replace(contextPath, "").replaceAll("/+", "/");
//        HandlerMapping handler = handlerMapping.get(url);
//
//        try {
//            ModelAndView mv = (ModelAndView) handler.getMethod().invoke(handler.getController());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        // 对象.方法名才能调用
        // 对象要从 ioc 容器中获取
//        method.invoke(context,);

//        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        HandlerMapping handlerMapping = getHandler(req);
        HandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);
        ModelAndView modelAndView = handlerAdapter.handle(req, resp, handlerMapping);
        processDispatchResult(resp, modelAndView);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView modelAndView) {

        // 调用 viewResolver 的 resoleView 方法
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handlerMapping) {
        return null;
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 相当于把 IOC 容器初始化了
        MaoApplicationContext context = new MaoApplicationContext(config.getInitParameter(LOCATION));

        initStrategies(context);

    }

    protected void initStrategies(MaoApplicationContext context) {

        // 有九种策略
        // 针对于每个用户请求，都会经过一些处理的策略之后，最终才能有结果输出
        // 每种策略可以自定义干预，但是最终结果都是一致的
        // modelAndView

        // ================== 这里就是传说中的九大组件 ================
        initMultipartResolver(context); // 文件上传解析
        initLocaleResolver(context); // 本地化解析
        initThemeResolver(context); // 主题解析

        // 自己实现
        // HandlerMapping 用来保存 Controller 中配置的 RequestMapping 和 Method 的一个对应关系
        initHandlerMappings(context); // 通过 HandlerMapping，将请求映射到处理器
        // HandlerAdapter 用来动态匹配参数，包括类转换，动态赋值
        initHandlerAdapters(context);  // 通过 HandlerAdapter，进行多类型的参数动态匹配

        initHandlerExceptionResolvers(context); // 如果执行中遇到异常，将有 HandlerExceptionResolver 来解析
        initRequestToViewNameTranslator(context); // 直接解析请求到视图名

        // 自己实现
        // 通过 ViewResolvers 实现动态模版解析
        // 自己解析一套模版语言
        initViewResolvers(context); // 通过 ViewResolvers 解析逻辑视图到具体视图实现

        initFlashMapManager(context); // flash 映射管理器
    }

    private void initFlashMapManager(MaoApplicationContext context) {

    }

    private void initViewResolvers(MaoApplicationContext context) {

    }

    private void initRequestToViewNameTranslator(MaoApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(MaoApplicationContext context) {

    }

    private void initHandlerAdapters(MaoApplicationContext context) {

    }

    // 将 Controller 中配置的 RequestMapping 和 Method 的一一对应
    private void initHandlerMappings(MaoApplicationContext context) {

        // 按照通常的理解应该是一个 map
        // Map<String,Method> map;
        // map.put(url,method);

        // 首先从容器中取到所有的实例
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {

        }
    }

    private void initThemeResolver(MaoApplicationContext context) {

    }

    private void initLocaleResolver(MaoApplicationContext context) {

    }

    private void initMultipartResolver(MaoApplicationContext context) {

    }
}
