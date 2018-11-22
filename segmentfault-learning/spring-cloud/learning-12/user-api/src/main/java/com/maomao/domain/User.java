package com.maomao.domain;

import java.io.Serializable;

/**
 * 用户模型
 *
 * @author senmao.li
 * @since 2018/11/21 15:53
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8794064868071396333L;
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
