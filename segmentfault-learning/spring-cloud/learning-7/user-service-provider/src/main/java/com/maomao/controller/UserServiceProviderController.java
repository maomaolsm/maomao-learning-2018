package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户提供方 controller
 * <p>
 * Created by maomao on 2018/12/4.
 */
@RestController
public class UserServiceProviderController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public boolean user(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
