package com.maomao.spring1.demo.action;

import com.maomao.spring1.demo.service.IDemoService;
import com.maomao.spring1.framework.annotation.Autowired;
import com.maomao.spring1.framework.annotation.Controller;
import com.maomao.spring1.framework.annotation.RequestMapping;
import com.maomao.spring1.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maomao on 2018/8/13.
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {

    @Autowired
    private IDemoService demoService;


    @RequestMapping("/query.json")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam("name") String name) {

        String result = demoService.get(name);

        // 验证 service 是否被注入
        System.out.println("~~~~~~~~~~~~~~~~~~ spring1 test " + result);

//        try {
//            response.getWriter().write(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @RequestMapping("/edit.json")
    public void edit(HttpServletRequest request, HttpServletResponse response,
                     Integer id) {

    }


}
