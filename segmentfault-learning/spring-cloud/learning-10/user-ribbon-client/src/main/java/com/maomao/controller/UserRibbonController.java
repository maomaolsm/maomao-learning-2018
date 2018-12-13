package com.maomao.controller;

import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by maomao on 2018/12/13.
 */
@RestController
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("")
    public String index() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("maomao");

        // 选择指定的 service Id
        ServiceInstance serviceInstance = loadBalancerClient.choose(
            "user-service-provider");

        return loadBalancerClient.execute(
            "user-service-provider", serviceInstance,
            instance -> {

                String url = instance.getUri() + "/user/save";

                RestTemplate restTemplate = new RestTemplate();

                return restTemplate.postForObject(url, user, String.class);
            });

    }

    @GetMapping("/user/list")
    public List<User> getAll(User user) {
        return Collections.emptyList();
    }

}
