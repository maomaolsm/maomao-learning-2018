package com.maomao.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Created by maomao on 2018/12/15.
 */
public class UserRibbonClientHystrixCommand extends HystrixCommand<List> {

    private final RestTemplate restTemplate;

    public UserRibbonClientHystrixCommand(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("user-ribbon-client"), 100);
        this.restTemplate = restTemplate;
    }

    @Override
    protected List run() throws Exception {
        return restTemplate.getForObject("http://user-service-provider", List.class);
    }

    protected List getFallback() {
        return Collections.emptyList();
    }
}
