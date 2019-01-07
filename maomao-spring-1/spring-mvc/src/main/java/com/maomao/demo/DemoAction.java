package com.maomao.demo;

import com.maomao.framework.annotation.Autowired;
import com.maomao.framework.annotation.Controller;
import com.maomao.framework.annotation.RequestMapping;
import com.maomao.framework.annotation.RequestParam;
import com.maomao.framework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public ModelAndView query(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam("name") String name) {

        String result = service.get(name);
        System.out.println("------------------ hello --------" + result);

        return out(response, result);
    }

    private ModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
