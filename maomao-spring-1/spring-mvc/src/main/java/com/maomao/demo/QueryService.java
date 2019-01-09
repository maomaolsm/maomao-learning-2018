package com.maomao.demo;


import com.maomao.framework.annotation.Service;

/**
 * Created by maomao on 2018/8/15.
 */
@Service
public class QueryService implements IQueryService {
    @Override
    public String get(String name) {
        return "-------------my name is : " + name;
    }

    @Override
    public String query(String teacher) {
        return "----------- " + teacher;
    }
}
