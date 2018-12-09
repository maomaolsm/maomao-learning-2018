package com.maomao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by maomao on 2018/12/6.
 */
@RestController
public class DemoRestController {

    private final static Random RANDOM = new Random();

    /**
     * 执行时间超过 100ms 时，触发异常
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public String index() throws Exception {

        long executeTime = RANDOM.nextInt(200);

        if (executeTime>100) { // 执行时间超过了 100ms
            throw new TimeoutException("Exception is timeout");
        }

        return "hello word";
    }

}
