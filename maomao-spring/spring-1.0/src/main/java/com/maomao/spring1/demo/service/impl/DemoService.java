package com.maomao.spring1.demo.service.impl;

import com.maomao.spring1.demo.service.IDemoService;
import com.maomao.spring1.framework.annotation.Service;

/**
 * Created by maomao on 2018/8/15.
 */
@Service
public class DemoService implements IDemoService {
    public String get(String name) {
        return "my name is : " + name;
    }
}
