package com.maomao.springaop.demo.service.impl;

import com.maomao.springaop.demo.service.IQueryService;
import com.maomao.springaop.framework.annotation.MaoService;

/**
 * Created by maomao on 2018/8/15.
 */
@MaoService
public class QueryService implements IQueryService {
    public String get(String name) {
        return "-------------my name is : " + name;
    }

    @Override
    public String query(String teacher) {
        return null;
    }
}
