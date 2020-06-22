package com.maomao.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = "text/plain;charset=utf-8")
    public String loginSuccess() {
        return getUsername() + "登录成功";
    }

    @RequestMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String r1() {
        return getUsername() + "访问r1";
    }

    @RequestMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
    public String r2() {
        return getUsername() + "访问r2";
    }

    private String getUsername() {
        String username = null;
        // 当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 用户身份
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        } else {
            assert principal != null;
            username = principal.toString();
        }
        return username;
    }

}
