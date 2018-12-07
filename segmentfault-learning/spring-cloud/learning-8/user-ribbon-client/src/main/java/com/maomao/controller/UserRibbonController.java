package com.maomao.controller;

import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/6 11:36
 */
@RestController
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping
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

                    String url = "http://localhost:9090/user/save";

                    RestTemplate restTemplate = new RestTemplate();

                    return restTemplate.postForObject(url, user, String.class);
                });

    }
}
