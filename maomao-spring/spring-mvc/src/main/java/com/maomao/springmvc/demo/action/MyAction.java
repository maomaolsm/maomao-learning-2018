package com.maomao.springmvc.demo.action;

import com.maomao.springmvc.demo.service.IDemoService;
import com.maomao.springmvc.framework.annotation.Autowired;
import com.maomao.springmvc.framework.annotation.Controller;
import com.maomao.springmvc.framework.annotation.RequestMapping;


/**
 * Created by maomao on 2018/8/15.
 */
@Controller
public class MyAction {

    @Autowired
    IDemoService demoService;

    @RequestMapping("/index.html")
    public void query() {

    }
}
