package com.maomao.spring.webmvc.auto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maomao on 2018/8/12.
 */
@RestController
public class DemoController {

    @GetMapping
    public String index(){
        return "自动装配";
    }
}
