package com.maomao.service;


import com.maomao.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);

    List<User> findByName(String name);

    User findByTel(String tel);

    void addUserRole(Long userId, Set<Long> roleIds);

    void updateUserRole(Long userId, Set<Long> roleIds);

}