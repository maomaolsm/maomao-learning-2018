package com.maomao.springaop.demo.action;

import com.maomao.springaop.demo.service.IQueryService;
import com.maomao.springaop.framework.annotation.MaoAutowired;
import com.maomao.springaop.framework.annotation.MaoController;
import com.maomao.springaop.framework.annotation.MaoRequestMapping;
import com.maomao.springaop.framework.annotation.MaoRequestParam;
import com.maomao.springaop.framework.webmvc.MaoModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maomao on 2018/8/28.
 */
@MaoController
@MaoRequestMapping("/")
public class PageAction {

    @MaoAutowired
    IQueryService queryService;

    @MaoRequestMapping("/first.json")
    public MaoModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                 @MaoRequestParam("teacher") String teacher) {

        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new MaoModelAndView("first.html", model);
    }
}
