package com.maomao.domain;

/**
 * 用户模型
 * <p>
 * Created by maomao on 2018/12/3.
 */
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
