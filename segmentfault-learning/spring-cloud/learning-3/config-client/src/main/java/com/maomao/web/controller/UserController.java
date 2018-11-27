package com.maomao.web.controller;

import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/11/27 17:33
 */
@RestController
@EnableConfigurationProperties(User.class)
public class UserController {

    // 通过构造器注入
    private final User user;

    @Autowired
    public UserController(User user) {
        this.user = user;
    }

    @GetMapping("/user")
    public User user() {
        return user;
    }
}
