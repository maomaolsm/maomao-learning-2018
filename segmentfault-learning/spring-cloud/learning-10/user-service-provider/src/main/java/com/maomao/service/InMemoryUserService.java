package com.maomao.service;

import com.maomao.api.UserService;
import com.maomao.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/11 14:57
 */
@Service("inMemoryUserService") // bean 名称
public class InMemoryUserService implements UserService {

    private Map<Long, User> map = new ConcurrentHashMap<>();

    @Override
    public boolean saveUser(User user) {
        return map.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {

        User user = new User();
        user.setId(999L);
        user.setName("maomao");

        map.put(user.getId(), user);

        return new ArrayList<>(map.values());
    }
}
