package com.maomao.springaop.demo.action;

import com.maomao.springaop.demo.service.IModifyService;
import com.maomao.springaop.demo.service.IQueryService;
import com.maomao.springaop.framework.annotation.Autowired;
import com.maomao.springaop.framework.annotation.RequestMapping;
import com.maomao.springaop.framework.annotation.RequestParam;
import com.maomao.springaop.framework.annotation.Controller;
import com.maomao.springaop.framework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by maomao on 2018/8/15.
 */
@Controller
@RequestMapping("/web")
public class MyAction {

    @Autowired
    IQueryService queryService;

    @Autowired
    IModifyService modifyService;

    @RequestMapping("/query.json")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("name") String name) {

        String result = queryService.get(name);
        // 验证 service 是否被注入
        System.out.println("~~~~~~~~~~~~~~~~~~~~ spring mvc by my action " + result);
        return out(response, result);
    }

    @RequestMapping("/add*.json")
    // 支持正则 addA addB
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("name") String name,
                            @RequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        return out(response, result);
    }

    @RequestMapping("/remove.json")
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @RequestMapping("/edit.json")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("id") Integer id,
                             @RequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }

    private ModelAndView out(HttpServletResponse response, String result) {

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
