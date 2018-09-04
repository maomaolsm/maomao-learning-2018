package com.maomao.springmvc.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * 解耦，专人干专事
 * Created by maomao on 2018/8/28.
 */
public class HandlerAdapter {

    private Map<String, Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    /**
     * @param req
     * @param resp
     * @param handlerMapping 为什么要把 handler 传进来
     *                       因为 handle 中包含了 controller、method、url 信息
     * @return
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,
                               HandlerMapping handlerMapping) throws Exception {
        // 根据用户的请求的参数信息，跟 method 中的参数信息进行动态匹配
        // resp 传进来的目的只有一个：只是为了将其赋值给方法参数，仅此而已

        // 只有当用户传过来的 modelAndView 为空的时候，才会 new 一个默认值

        // 1、要准备好这个方法的形参列表
        // 方法的重载：形参的决定因素：参数的个数、参数的类型、参数的顺序、方法的名字
        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();

        // 2、拿到自定义命名参数所在的位置
        // 用户通过 URL 传过来的参数列表
        Map<String, String[]> reqParameterMap = req.getParameterMap();

        // 3、构造实参列表
        Object[] paramValues = new Object[paramTypes.length];
        for (Map.Entry<String, String[]> param : reqParameterMap.entrySet()) {
            String value = Arrays
                    .toString(param.getValue())
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", "");

            if (!this.paramMapping.containsKey(param.getKey())) {
                continue;
            }

            int index = this.paramMapping.get(param.getKey());

            // 因为页面上传过来的值都是 string 类型的，而在方法中定义的类型是千变万化的
            // 要针对我们传过来的参数进行类型转换
            paramValues[index] = caseStringValue(value, paramTypes[index]);
        }

        if (HttpServletRequest.class.getName() != null) {
            int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if (HttpServletResponse.class.getName() != null) {
            int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        // 4、从 handler 中取出 controller、method，然后利用反射机制进行调用
        Object result = handlerMapping.getMethod()
                .invoke(handlerMapping.getController(), paramValues);

        if (result == null) {
            return null;
        }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == ModelAndView.class;
        if (isModelAndView) {
            return (ModelAndView) result;
        } else {
            return null;
        }
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }
}
