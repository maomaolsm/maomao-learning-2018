package com.maomao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/23 11:11
 */
@Controller
public class HelloWorldController {


    @RequestMapping("")
    public String index(@RequestParam int value,
//            @RequestHeader("Accept-Language") String acceptLanguage,
                        Model model) {
//        model.addAttribute("acceptLanguage", acceptLanguage);
//        model.addAttribute("message","hello word !");
        return "index";
    }

    /**
     * 第一次需要先请求 / ,之后才能获取到 CookieValue，否则获取不到 CookieValue
     *
     * @param jsessionId
     * @param model
     * @return
     */
    @RequestMapping("/test")
    public String index(@CookieValue("JSESSIONID") String jsessionId,
                        Model model) {
        model.addAttribute("jsessionId", jsessionId);
        return "index";
    }
}
