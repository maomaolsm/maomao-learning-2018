package com.maomao.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by maomao on 2019/1/5.
 */
public class HandlerMapping {

    private Object controller;

    private Method method;

    private Pattern pattern;

    public HandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
