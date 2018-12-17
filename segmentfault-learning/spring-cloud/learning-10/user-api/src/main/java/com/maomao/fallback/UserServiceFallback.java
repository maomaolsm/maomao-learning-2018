package com.maomao.fallback;

import com.maomao.api.UserService;
import com.maomao.domain.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by maomao on 2018/12/16.
 */
public class UserServiceFallback implements UserService {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }
}
