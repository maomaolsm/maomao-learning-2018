package com.maomao.spring1.demo.action;

import com.maomao.spring1.demo.service.IDemoService;
import com.maomao.spring1.framework.annotation.Autowired;
import com.maomao.spring1.framework.annotation.Controller;
import com.maomao.spring1.framework.annotation.RequestMapping;

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
