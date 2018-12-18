package com.maomao.service;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/18 14:14
 */
@Service
public class InMemoryUserService implements UserService {

    private Map<Long, User> map = new ConcurrentHashMap<>();

    @Override
    public boolean save(User user) {
        return map.put(user.getId(), user) == null;
    }

    @Override
    public List<User> getAll() {

        User user = new User();
        user.setId(999L);
        user.setName("maomao");

        map.put(user.getId(), user);

        return new ArrayList<>(map.values());
    }
}
