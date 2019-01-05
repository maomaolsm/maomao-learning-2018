package com.maomao.demo;

import com.maomao.framework.annotation.Autowired;
import com.maomao.framework.annotation.Controller;
import com.maomao.framework.annotation.RequestMapping;

/**
 * Created by maomao on 2018/12/26.
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {

    @Autowired
    // 注入的是接口，如果不是接口，则会注入报错
    private IDemoService service;

    @RequestMapping("/query.json")
    public void query() {

        String result = service.get("maomao");
        System.out.println("------------------ hello --------" + result);

    }
}
