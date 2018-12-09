package com.maomao.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by maomao on 2018/12/9.
 */
public class UserRibbonClientHystrixCommand extends HystrixCommand<Collection> {

    private final RestTemplate restTemplate;

    public UserRibbonClientHystrixCommand(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("user-ribbon-client"), 100);
        this.restTemplate = restTemplate;
    }

    @Override
    protected Collection run() throws Exception {
        return restTemplate.getForObject("http://user-service-provider/user/list", Collection.class);
    }

    protected Collection getFallback() {
        return Collections.emptyList();
    }
}
