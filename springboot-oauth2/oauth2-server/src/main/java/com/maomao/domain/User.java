package com.maomao.domain;

import com.maomao.domain.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String name;
    private String avatarUrl;
    private String username;
    private String password;
    private String tel;
    private Gender gender;
    private Date createTime;
    private Date lastLoginTime;

}
