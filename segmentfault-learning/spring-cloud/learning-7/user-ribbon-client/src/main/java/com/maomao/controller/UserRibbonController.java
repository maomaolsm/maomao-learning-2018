package com.maomao.controller;

import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by maomao on 2018/12/3.
 */
@RestController
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${provider.service.name}")
    private String providerServiceName;

    @GetMapping
    public String index() throws IOException {

        User user = new User();
        user.setId(1L);
        user.setName("maomao");

        // 选择指定的 service Id
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerServiceName);

        return loadBalancerClient.execute(providerServiceName,
            serviceInstance, instance -> {

                // 服务器实例，获取 主机名（IP）和端口
                String host = instance.getHost();
                int port = instance.getPort();

                String url = "http://" + host + ":" + port + "/user/save";

                RestTemplate restTemplate = new RestTemplate();

                return restTemplate.postForObject(url, user, String.class);
            });
    }
}
