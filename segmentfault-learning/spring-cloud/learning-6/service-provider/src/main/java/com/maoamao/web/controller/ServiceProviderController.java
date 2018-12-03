package com.maoamao.web.controller;

import com.maoamao.domain.User;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/greeting")
    public String greeting(@RequestBody User user) {
        return "greeting , " + user + " on port : " + port;
    }

}
