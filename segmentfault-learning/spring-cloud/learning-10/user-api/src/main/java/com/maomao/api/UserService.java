package com.maomao.api;

import com.maomao.domain.User;

import java.util.List;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/11 11:19
 */
public interface UserService {

    boolean saveUser(User user);

    List<User> findAll();

}
