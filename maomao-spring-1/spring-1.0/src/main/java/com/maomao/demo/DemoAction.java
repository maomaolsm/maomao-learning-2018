package com.maomao.demo;

import com.maomao.spring.annotation.Autowired;
import com.maomao.spring.annotation.Controller;
import com.maomao.spring.annotation.RequestMapping;
import com.maomao.spring.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maomao on 2018/12/26.
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {

    @Autowired
    private IDemoService iDemoService;

    @RequestMapping("/query.json")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam("name") String name) {

        String result = iDemoService.get(name);

        System.out.println("---------------------------- 出来了：" + result);

    }

}
