package com.maomao.springaop.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by maomao on 2018/8/28.
 */
public class MaoHandlerMapping {

    private Pattern pattern;
    private Object controller;
    private Method method;

    public MaoHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.controller = controller;
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
