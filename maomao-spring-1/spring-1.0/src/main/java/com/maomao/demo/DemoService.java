package com.maomao.demo;

import com.maomao.spring.annotation.Service;

/**
 * Created by maomao on 2018/12/26.
 */
@Service
public class DemoService implements IDemoService {
    public String get(String name) {
        return " demo service : " + name;
    }
}
