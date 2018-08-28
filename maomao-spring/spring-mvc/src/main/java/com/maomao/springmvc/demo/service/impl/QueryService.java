package com.maomao.springmvc.demo.service.impl;

import com.maomao.springmvc.demo.service.IQueryService;
import com.maomao.springmvc.framework.annotation.Service;

/**
 * Created by maomao on 2018/8/15.
 */
@Service
public class QueryService implements IQueryService {
    public String get(String name) {
        return "-------------my name is : " + name;
    }

    @Override
    public String query(String teacher) {
        return null;
    }
}
