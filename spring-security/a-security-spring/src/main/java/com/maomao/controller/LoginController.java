package com.maomao.controller;

import com.maomao.model.AuthenticationRequest;
import com.maomao.model.UserDto;
import com.maomao.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto userDto = authenticationService.authentication(authenticationRequest);

        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername();
    }

    @GetMapping(value = "logout", produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session) {
        session.invalidate();
        return "chenggong";
    }

    @GetMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String r1(HttpSession session) {
        String name;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {
            name = "niming";
        } else {
            UserDto userDto = (UserDto) object;
            name = userDto.getUsername();
        }
        return name + "访问r1";
    }

    @GetMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
    public String r2(HttpSession session) {
        String name;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {
            name = "niming";
        } else {
            UserDto userDto = (UserDto) object;
            name = userDto.getUsername();
        }
        return name + "访问r2";
    }
}
