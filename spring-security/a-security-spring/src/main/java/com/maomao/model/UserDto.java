package com.maomao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    public static final String SESSION_USER_KEY = "_user";

    private int id;
    private String username;
    private String password;

    // 权限
    private Set<String> authorities;
}
