package com.maomao.annotationdrivendevelopment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maomao on 2018/8/11.
 */
@RestController
public class DemoController {

    @GetMapping("/hollerWord")
    public String hollerWord() {
        return "Holler, word!";
    }

}
