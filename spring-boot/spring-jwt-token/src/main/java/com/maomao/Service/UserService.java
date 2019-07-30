package com.maomao.Service;

import com.maomao.bean.User;
import org.springframework.stereotype.Service;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/26 11:48
 */
@Service
public class UserService {
    public User findUserById(String userId) {

        if (!"1".equals(userId)) {
            return null;
        }

        User user = new User();
        user.setId("1");
        user.setUsername("name");
        user.setPassword("mima");
        return user;
    }

    public User findByUsername(User user) {
        if (!"name".equals(user.getUsername())) {
            return null;
        }

        User u = new User();
        u.setId("1");
        u.setUsername("name");
        u.setPassword("mima");
        return u;
    }
}
