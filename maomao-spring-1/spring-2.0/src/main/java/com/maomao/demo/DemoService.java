package com.maomao.demo;

import com.maomao.framework.annotation.Service;

/**
 * Created by maomao on 2018/12/27.
 */
@Service
public class DemoService implements IDemoService {

    @Override
    public String get(String name) {
        return "name in service : " + name;
    }
}
