package com.maomao.springaop.framework.webmvc.servlet;

import com.maomao.springaop.framework.annotation.MaoController;
import com.maomao.springaop.framework.annotation.MaoRequestMapping;
import com.maomao.springaop.framework.annotation.MaoRequestParam;
import com.maomao.springaop.framework.aop.MaoAopProxyUtils;
import com.maomao.springaop.framework.context.MaoApplicationContext;
import com.maomao.springaop.framework.webmvc.MaoHandlerAdapter;
import com.maomao.springaop.framework.webmvc.MaoHandlerMapping;
import com.maomao.springaop.framework.webmvc.MaoModelAndView;
import com.maomao.springaop.framework.webmvc.MaoViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * servlet 只是作为一个 MVC 的启动入口
 * <p>
 * Created by maomao on 2018/8/19.
 */
public class MaoDispatchServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

//    private Map<String, HandlerMapping> handlerMapping = new HashMap<String, HandlerMapping>();

    // 课后思考一下这样设计的经典之处 为什么是 list
    // HandlerMapping 是 spring 中最核心的设计，也是最经典的
    // 他牛逼到直接干掉了其他的 mvc 框架
    private List<MaoHandlerMapping> handlerMappings = new ArrayList<MaoHandlerMapping>();

    private Map<MaoHandlerMapping, MaoHandlerAdapter> handlerAdapters = new HashMap<MaoHandlerMapping, MaoHandlerAdapter>();

    private List<MaoViewResolver> viewResolvers = new ArrayList<MaoViewResolver>();

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
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter()
                .write("--------------- 500 fu wu qi cuo wu exception, details : \r\n"
                    + Arrays.toString(e.getStackTrace())
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", "\r\n"));
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp)
        throws Exception {

        // 根据用户请求的 url 来获得一个 handler
        MaoHandlerMapping handlerMapping = getHandler(req);
        if (handlerMapping == null) {
            resp.getWriter().write("------- 404 mei you zhao dao : \r\n");
        }

        MaoHandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);

        // 这一步只是调用方法，得到返回值
        MaoModelAndView modelAndView = handlerAdapter.handle(req, resp, handlerMapping);

        // 这一步才是真正的输出
        processDispatchResult(resp, modelAndView);
    }

    private void processDispatchResult(HttpServletResponse resp,
                                       MaoModelAndView mv) throws Exception {

        // 调用 viewResolver 的 resoleView 方法
        if (null == mv) {
            return;
        }

        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (MaoViewResolver viewResolver : this.viewResolvers) {
            if (!mv.getViewName().equals(viewResolver.getViewName())) {
                continue;
            }
            String out = viewResolver.viewResolver(mv);
            if (out != null) {
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private MaoHandlerAdapter getHandlerAdapter(MaoHandlerMapping handlerMapping) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handlerMapping);
    }

    private MaoHandlerMapping getHandler(HttpServletRequest req) {

        if (this.handlerMappings.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (MaoHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }

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

        // 在页面上敲一个 http://localhost/first.html
        // 解决页面名字和模版文件关联的问题

        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templatePath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templatePath);

        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new MaoViewResolver(template.getName(), template));
        }

    }

    private void initRequestToViewNameTranslator(MaoApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(MaoApplicationContext context) {

    }

    private void initHandlerAdapters(MaoApplicationContext context) {
        // 在初始化阶段，我们能做的就是，将这些参数的名字或者类型按一定的顺序保存下来
        // 因为后面用反射的时候，传的形参是一个数组
        // 可以通过记录这些参数的位置 index，挨个从数组中填值，这样的话，就和参数的顺序无关了

        for (MaoHandlerMapping handlerMapping : this.handlerMappings) {

            // 每一个方法有一个参数列表，那么这里保存的是形参列表
            Map<String, Integer> paramMapping = new HashMap<String, Integer>();

            // 这里只是处理命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof MaoRequestParam) {
                        String paramName = ((MaoRequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            // 接下来我们处理非命名参数
            // 只处理 request 和 response
            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }

            this.handlerAdapters.put(handlerMapping, new MaoHandlerAdapter(paramMapping));
        }
    }

    // 将 Controller 中配置的 RequestMapping 和 Method 的一一对应
    private void initHandlerMappings(MaoApplicationContext context) {

        // 按照通常的理解应该是一个 map
        // Map<String,Method> map;
        // map.put(url,method);

        // 首先从容器中取到所有的实例
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {

                // 到了 mvc 层，对外提供的方法只有一个 getBean 方法
                // 返回的对象不是 BeanWrapper，怎么办？
                Object proxy = context.getBean(beanName);
                Object controller = MaoAopProxyUtils.getTargetObject(proxy);
                Class<?> clazz = controller.getClass();
                // 但是不是所有的牛奶都叫特仑苏
                if (!clazz.isAnnotationPresent(MaoController.class)) {
                    continue;
                }

                String baseUrl = "";

                if (clazz.isAnnotationPresent(MaoRequestMapping.class)) {
                    MaoRequestMapping maoRequestMapping = clazz.getAnnotation(MaoRequestMapping.class);
                    baseUrl = maoRequestMapping.value();
                }

                // 扫描所有的 public 方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {

                    if (!method.isAnnotationPresent(MaoRequestMapping.class)) {
                        continue;
                    }

                    MaoRequestMapping maoRequestMapping = method.getAnnotation(MaoRequestMapping.class);
                    String regex = ("/" + baseUrl + maoRequestMapping.value().replaceAll("\\*", ".*"))
                        .replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new MaoHandlerMapping(pattern, controller, method));

                    System.out.println("------- mapping : " + regex + " , " + method);
                }

            }
        } catch (Exception e) {

        }
    }

    private void initThemeResolver(MaoApplicationContext context) {

    }

    private void initLocaleResolver(MaoApplicationContext context) {

    }

    private void initMultipartResolver(MaoApplicationContext context) {

    }
}
