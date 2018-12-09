package com.maomao.controller;

import com.maomao.domain.User;
import com.maomao.hystrix.UserRibbonClientHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

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

    @Autowired
    private RestTemplate restTemplate;

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

    /**
     * 调用 user-service-provider "/user/list" rest 接口，并直接返回内容
     * 增加短路功能
     *
     * @return
     */
    @GetMapping("/user-service-provider/user/list")
    public Collection<User> getUserList() {

//        return restTemplate.getForObject("http://user-service-provider/user/list", Collection.class);

        return new UserRibbonClientHystrixCommand(restTemplate).execute();

    }
}
