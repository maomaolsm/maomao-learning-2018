package com.maomao.api;

import com.maomao.domain.User;

import java.util.List;

/**
 * Created by maomao on 2018/12/18.
 */
public interface UserService {

    boolean save(User user);

    List<User> getAll();

}
