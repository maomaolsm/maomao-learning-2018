package com.maomao.demo;

import com.maomao.framework.annotation.Autowired;
import com.maomao.framework.annotation.Controller;
import com.maomao.framework.annotation.RequestMapping;
import com.maomao.framework.annotation.RequestParam;
import com.maomao.framework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maomao on 2018/8/28.
 */
@Controller
@RequestMapping("/")
public class PageAction {

    @Autowired
    IQueryService queryService;

    @RequestMapping("/first.json")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("teacher") String teacher) {

        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new ModelAndView("first.html", model);
    }
}
