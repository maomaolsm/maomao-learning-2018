package com.maomao.framework.webmvc;

import java.util.regex.Pattern;

/**
 * Created by maomao on 2019/1/5.
 */
public class HandlerMapping {

    private String url;

    private Object controller;

    private Pattern pattern;

    public HandlerMapping(String url, Object controller, Pattern pattern) {
        this.url = url;
        this.controller = controller;
        this.pattern = pattern;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
