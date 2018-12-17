package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by maomao on 2018/12/16.
 */
@RestController
public class UserServiceClietnController implements UserService {

    @Autowired
    private UserService userService;

    // 通过方法继承，url 映射：/user/save
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 通过方法继承，url 映射：/user/find/all
    @Override
    public List<User> findAll() {
        return userService.findAll();
    }
}
