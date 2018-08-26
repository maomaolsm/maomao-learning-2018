package com.maomao.demo.mvc.action;

import com.maomao.demo.service.IDemoService;
import com.maomao.spring2.annotation.Autowired;
import com.maomao.spring2.annotation.Controller;
import com.maomao.spring2.annotation.RequestMapping;
import com.maomao.spring2.annotation.RequestParam;

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
        System.out.println("-----------" + result);

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
