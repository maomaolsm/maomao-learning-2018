package com.maoamao.web.controller;

import com.maoamao.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供方
 * <p>
 * Created by maomao on 2018/11/29.
 */
@RestController
public class ServiceProviderController {

    @PostMapping("/greeting")
    public String greeting(@RequestBody User user) {
        return "greeting , " + user;
    }

}
