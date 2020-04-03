package com.maomao.service;

import com.maomao.domain.User;
import com.maomao.mapper.UserMapper;
import com.maomao.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;


    @Override
    public User findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public User findByTel(String tel) {
        return mapper.findByTel(tel);
    }

    @Override
    public void addUserRole(Long userId, Set<Long> roleIds) {

    }

    @Override
    public void updateUserRole(Long userId, Set<Long> roleIds) {

    }


    @Override
    public List<User> findByName(String name) {
        return mapper.findByName("%" + name + "%");
    }

}
