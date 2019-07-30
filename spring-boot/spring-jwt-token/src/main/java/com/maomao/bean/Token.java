package com.maomao.bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/25 20:14
 */
public class Token {
    public String getToken(User user) {
        String token;

        token = JWT
                .create()
                .withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}
