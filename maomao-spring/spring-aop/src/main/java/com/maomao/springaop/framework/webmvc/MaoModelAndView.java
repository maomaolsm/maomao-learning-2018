package com.maomao.springaop.framework.webmvc;

import java.util.Map;

/**
 * Created by maomao on 2018/8/28.
 */
public class MaoModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public MaoModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
