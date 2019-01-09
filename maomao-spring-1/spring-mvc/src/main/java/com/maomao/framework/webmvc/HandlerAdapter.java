package com.maomao.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by maomao on 2019/1/5.
 */
public class HandlerAdapter {

    private Map<String, Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) {
        return null;
    }
}
