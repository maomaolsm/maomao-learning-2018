package com.maomao.demo.service.impl;

import com.maomao.spring2.annotation.Service;
import com.maomao.demo.service.IDemoService;

/**
 * Created by maomao on 2018/8/15.
 */
@Service
public class DemoService implements IDemoService {
    public String get(String name) {
        return "-------------my name is : " + name;
    }
}
