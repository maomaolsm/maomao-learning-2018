package com.maomao.api;

import com.maomao.domain.User;

import java.util.List;

/**
 * 用户服务
 *
 * Created by maomao on 2018/12/3.
 */

public interface UserService {

    boolean saveUser(User user);

    List<User> findAll();

}
