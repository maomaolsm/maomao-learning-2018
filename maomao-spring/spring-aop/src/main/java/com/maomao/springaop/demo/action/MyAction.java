package com.maomao.springaop.demo.action;

import com.maomao.springaop.demo.service.IModifyService;
import com.maomao.springaop.demo.service.IQueryService;
import com.maomao.springaop.framework.annotation.MaoAutowired;
import com.maomao.springaop.framework.annotation.MaoController;
import com.maomao.springaop.framework.annotation.MaoRequestMapping;
import com.maomao.springaop.framework.annotation.MaoRequestParam;
import com.maomao.springaop.framework.webmvc.MaoModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by maomao on 2018/8/15.
 */
@MaoController
@MaoRequestMapping("/web")
public class MyAction {

    @MaoAutowired
    IQueryService queryService;

    @MaoAutowired
    IModifyService modifyService;

    @MaoRequestMapping("/query.json")
    public MaoModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                 @MaoRequestParam("name") String name) {

        String result = queryService.get(name);
        // 验证 service 是否被注入
        System.out.println("~~~~~~~~~~~~~~~~~~~~ spring mvc by my action " + result);
        return out(response, result);
    }

    @MaoRequestMapping("/add*.json")
    // 支持正则 addA addB
    public MaoModelAndView add(HttpServletRequest request, HttpServletResponse response,
                               @MaoRequestParam("name") String name,
                               @MaoRequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        return out(response, result);
    }

    @MaoRequestMapping("/remove.json")
    public MaoModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                                  @MaoRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @MaoRequestMapping("/edit.json")
    public MaoModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                                @MaoRequestParam("id") Integer id,
                                @MaoRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }

    private MaoModelAndView out(HttpServletResponse response, String result) {

        try {
            response
                .getWriter()
                .write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
