package com.maomao.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("username=" + s);
        UserDetails userDetails = User.withUsername("zhan")
                .password("$2a$10$lY5aS.N8q3883kMNHmM6Q.TVwvqMMcxjRRbISOSJXxPsEaaN7Kuoi")
                .authorities("p1")
                .build();
        return userDetails;
    }
}
