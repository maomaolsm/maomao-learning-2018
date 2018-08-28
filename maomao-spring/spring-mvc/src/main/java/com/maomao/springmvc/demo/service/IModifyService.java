package com.maomao.springmvc.demo.service;

/**
 * Created by maomao on 2018/8/28.
 */
public interface IModifyService {
    String add(String name, String addr);

    String edit(Integer id, String name);

    String remove(Integer id);
}
