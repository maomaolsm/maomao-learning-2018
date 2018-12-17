package com.maomao.controller;

import com.maomao.domain.User;
import com.maomao.hystrix.UserRibbonClientHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by maomao on 2018/12/13.
 */
@RestController
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public String index() throws IOException {
        return loadBalancerClient.execute("user-service-provider", new LoadBalancerRequestImpl());
    }

    @GetMapping("/user/list")
    public List<User> getAll(User user) {
//        return restTemplate.getForObject(
//            "http://user-service-provider", List.class);

        return new UserRibbonClientHystrixCommand(restTemplate).execute();
    }

    public class LoadBalancerRequestImpl implements LoadBalancerRequest<String> {

        @Override
        public String apply(ServiceInstance instance) throws Exception {

            User user = new User();
            user.setId(1L);
            user.setName("maomao");

            String url = instance.getUri() + "/user/save";

            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.postForObject(url, user, String.class);
        }
    }
}
