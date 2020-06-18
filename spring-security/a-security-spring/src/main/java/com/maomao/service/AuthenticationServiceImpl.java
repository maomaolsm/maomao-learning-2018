package com.maomao.service;

import com.maomao.model.AuthenticationRequest;
import com.maomao.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        return getUserDto(authenticationRequest.getUsername());
    }

    private UserDto getUserDto(String username) {
        return userDtoMap.get(username);
    }

    private Map<String, UserDto> userDtoMap = new HashMap<>();

    {
        Set<String> set1 = new HashSet<>();
        set1.add("p1");
        Set<String> set2 = new HashSet<>();
        set2.add("p2");
        userDtoMap.put("z", new UserDto(1, "z", "1", set1));
        userDtoMap.put("l", new UserDto(2, "l", "1", set2));
    }


}
