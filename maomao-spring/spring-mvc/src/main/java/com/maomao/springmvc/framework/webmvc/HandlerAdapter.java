package com.maomao.springmvc.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解耦，专人干专事
 * Created by maomao on 2018/8/28.
 */
public class HandlerAdapter {

    /**
     *
     * @param req
     * @param resp
     * @param handlerMapping 为什么要把 handler 传进来
     *                       因为 handle 中包含了 controller、method、url 信息
     * @return
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) {
        // 根据用户的请求的参数信息，跟 method 中的参数信息进行动态匹配
        // resp 传进来的目的只有一个：只是为了将其赋值给方法参数，仅此而已

        // 只有当用户传过来的 modelAndView 为空的时候，才会 new 一个默认值
        return null;
    }
}
