package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by maomao on 2018/12/18.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/find/all")
    public List<User> getAll() {
        return userService.getAll();
    }

}
