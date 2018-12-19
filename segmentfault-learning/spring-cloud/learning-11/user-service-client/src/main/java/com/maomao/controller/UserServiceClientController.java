package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by maomao on 2018/12/19.
 */
@RestController
public class UserServiceClientController implements UserService{

    @Autowired
    private UserService userService;

    @Override
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}
