package com.maomao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = "text/plain;charset=utf-8")
    public String loginSuccess() {
        return "登录成功";
    }

    @RequestMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String r1() {
        return "访问r1";
    }

    @RequestMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
    public String r2() {
        return "访问r2";
    }

}
