package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by maomao on 2018/12/18.
 */
@RestController
public class UserController implements UserService {

    private final UserService userService;

    public UserController(@Qualifier("InMemoryUserService") UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

}
