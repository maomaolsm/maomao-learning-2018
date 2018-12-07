package com.maomao.service;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/12/6.
 */
@Service
public class InMemoryUserService implements UserService {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    @Override
    public boolean saveUser(User user) {
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {

        // 初始化数据
        User user = new User();
        user.setId(999L);
        user.setName("maomao");
        repository.put(999L, user);

        return new ArrayList<>(repository.values());
    }
}
