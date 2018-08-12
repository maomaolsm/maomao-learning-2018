package com.maomao.annotationdrivendevelopment.config;

import com.maomao.annotationdrivendevelopment.domian.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by maomao on 2018/8/11.
 */
@Configuration
public class UserConfiguration {

    @Bean
    public User user() {
        User user = new User();
        user.setName("茂茂");
        return user;
    }

}
