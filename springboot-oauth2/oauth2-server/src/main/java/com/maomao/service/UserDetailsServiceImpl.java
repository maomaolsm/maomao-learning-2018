package com.maomao.service;

import com.maomao.domain.Resource;
import com.maomao.domain.Role;
import com.maomao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Role> roles = roleService.findByUserId(user.getId());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        List<Resource> resources = resourceService.findByUserId(user.getId());
        for (Resource resource : resources){
            authorities.add(new SimpleGrantedAuthority(resource.getCode()));
        }

        return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),authorities);
    }

}
