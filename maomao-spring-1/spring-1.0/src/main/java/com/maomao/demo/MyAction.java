package com.maomao.demo;

import com.maomao.spring.annotation.Autowired;
import com.maomao.spring.annotation.Controller;
import com.maomao.spring.annotation.RequestMapping;
import com.maomao.spring.annotation.RequestParam;

/**
 * Created by maomao on 2018/12/26.
 */
@Controller
public class MyAction {
    @Autowired
    private IDemoService iDemoService;

    @RequestMapping("/index.html")
    public String query(@RequestParam String name) {
        return iDemoService.get(name);
    }

}
