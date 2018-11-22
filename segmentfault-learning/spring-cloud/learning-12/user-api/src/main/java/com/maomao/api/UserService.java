package com.maomao.api;

import com.maomao.domain.User;
import com.maomao.fallback.UserServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 用户服务
 *
 * @author senmao.li
 * @since 2018/11/21 16:04
 */
//利用占位符避免未来整合硬编码
@FeignClient(name = "${user.service.name}", fallback = UserServiceFallback.class)
public interface UserService {

    @PostMapping("/user/save")
    boolean saveUser(User user);

    @GetMapping("/user/find/all")
    List<User> findAll();
}
