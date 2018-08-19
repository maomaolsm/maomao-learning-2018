package com.maomao.demo.mvc.action;

import com.maomao.demo.service.IDemoService;
import com.maomao.spring.annotation.Autowired;
import com.maomao.spring.annotation.Controller;
import com.maomao.spring.annotation.RequestMapping;

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
