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

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/11 14:57
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final static Random RANDOM = new Random();

    @PostMapping("/user/save")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "100")},
            fallbackMethod = "fallbackGetAll"
    )
    @GetMapping("")
    public List<User> getAll() throws InterruptedException {

        long sleepTime = RANDOM.nextInt(200);

        System.out.println(sleepTime + "ms");

        Thread.sleep(sleepTime);

        return userService.findAll();
    }

    private List<User> fallbackGetAll() {
        return Collections.emptyList();
    }
}
