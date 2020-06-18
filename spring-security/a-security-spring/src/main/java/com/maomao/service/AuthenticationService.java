package com.maomao.service;

import com.maomao.model.AuthenticationRequest;
import com.maomao.model.UserDto;

public interface AuthenticationService {
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
