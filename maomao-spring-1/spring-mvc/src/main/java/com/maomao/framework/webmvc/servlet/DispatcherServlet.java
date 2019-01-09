package com.maomao.framework.webmvc.servlet;

import com.maomao.framework.annotation.Controller;
import com.maomao.framework.annotation.RequestMapping;
import com.maomao.framework.annotation.RequestParam;
import com.maomao.framework.context.ApplicationContext;
import com.maomao.framework.webmvc.HandlerAdapter;
import com.maomao.framework.webmvc.HandlerMapping;
import com.maomao.framework.webmvc.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by maomao on 2019/1/5.
 */
public class DispatcherServlet extends HttpServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<HandlerMapping, HandlerAdapter>();

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

        for (HandlerMapping handlerMapping : handlerMappings) {
            Map<String, Integer> paramMapping = new HashMap<String, Integer>();

            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation annotation : pa[i]) {
                    if (annotation instanceof RequestParam) {
                        String paramName = ((RequestParam) annotation).value();

                        // 为什么不处理等于空的情况
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }

            this.handlerAdapters.put(handlerMapping, new HandlerAdapter(paramMapping));

        }

    }

    private void initHandlerMappings(ApplicationContext context) {

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object controller = context.getBean(beanName);
            Class<?> clazz = controller.getClass();

            if (clazz.isAnnotationPresent(Controller.class)) {
                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        String regex = ("/" + baseUrl + requestMapping.value().replaceAll("/+", "/"));
                        Pattern pattern = Pattern.compile(regex);
                        this.handlerMappings.add(new HandlerMapping(controller, method, pattern));
                        System.out.println("mapping : " + regex + "," + method);
                    }
                }
            }
        }
    }

    private void initThemeResolver(ApplicationContext context) {

    }

    private void initLocaleResolver(ApplicationContext context) {
    }

    private void initMultipartResolver(ApplicationContext context) {

    }
}
