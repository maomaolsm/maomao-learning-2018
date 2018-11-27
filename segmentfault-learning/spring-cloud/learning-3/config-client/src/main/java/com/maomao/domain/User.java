package com.maomao.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用户
 *
 * @author senmao.li
 * @since 2018/11/27 17:27
 */
@ConfigurationProperties(prefix = "sf.user")
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
