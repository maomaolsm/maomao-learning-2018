package com.maomao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/23 11:11
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index(){
        return "index";
    }

}
