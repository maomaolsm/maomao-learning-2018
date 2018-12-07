package com.maomao.controller;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/6 11:16
 */
@RestController
public class UserController {

    private final static Random RANDOM = new Random();

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public boolean save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @HystrixCommand(commandProperties = { // command 配置
            // 设置超时时间为 100 ms
            @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "100")},
            fallbackMethod = "fallbackForGetUsers"
    )
    @GetMapping
    public Collection<User> getUsers() throws InterruptedException {

        long executeTime = RANDOM.nextInt(200);

        System.out.println("execute time : " + executeTime + " ms ");

        Thread.sleep(executeTime);

        return userService.findAll();
    }

    public Collection<User> fallbackForGetUsers() {
        return Collections.emptyList();
    }

}
