package com.maomao.fallback;

import com.maomao.api.UserService;
import com.maomao.domain.User;

import java.util.Collections;
import java.util.List;

/**
 * {@link UserService} fallback 实现
 *
 * @author senmao.li
 * @since 2018/11/21 16:05
 */
public class UserServiceFallback implements UserService{

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }
}
