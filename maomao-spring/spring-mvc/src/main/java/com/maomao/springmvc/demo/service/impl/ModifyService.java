package com.maomao.springmvc.demo.service.impl;

import com.maomao.springmvc.demo.service.IModifyService;

/**
 * Created by maomao on 2018/8/28.
 */
public class ModifyService implements IModifyService {
    @Override
    public String add(String name, String addr) {
        return "----------- name : " + name + " and addr : " + addr;
    }

    @Override
    public String edit(Integer id, String name) {
        return null;
    }

    @Override
    public String remove(Integer id) {
        return null;
    }
}
