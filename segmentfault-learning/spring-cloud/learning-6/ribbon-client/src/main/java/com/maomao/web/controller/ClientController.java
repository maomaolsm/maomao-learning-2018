package com.maomao.web.controller;

import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by maomao on 2018/11/29.
 */
@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-provider-host}")
    private String serviceProviderHost;

    @Value("${service-provider-port}")
    private Integer serviceProviderPort;

    @Value("${service-provider-name}")
    private String serviceProviderName;

    @GetMapping
    public String index() {

        User user = new User();
        user.setId(1L);
        user.setName("maomao");

//        return restTemplate.postForObject(
//            "http://" +
//                serviceProviderHost + ":" + serviceProviderPort +
//                "/greeting",
//            user, String.class
//        );

        // serviceProviderName 解析为 ip + 端口 ，可为什么还要加 http://
        return restTemplate.postForObject(
            "http://" + serviceProviderName + "/greeting",
//             serviceProviderName + "/greeting",
            user, String.class
        );
    }
}
