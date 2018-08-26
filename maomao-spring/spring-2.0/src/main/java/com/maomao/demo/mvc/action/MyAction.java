package com.maomao.demo.mvc.action;

import com.maomao.spring2.annotation.Autowired;
import com.maomao.spring2.annotation.Controller;
import com.maomao.spring2.annotation.RequestMapping;
import com.maomao.demo.service.IDemoService;


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
