package com.maomao.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maomao.bean.User;
import org.springframework.stereotype.Service;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/29 15:03
 */
@Service
public class TokenService {
    public String getToken(User user) {
        String token;
        token = JWT.create().withAudience(user.getId()) // 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword())); // 以 password 作为 token 的密钥
        return token;
    }
}
