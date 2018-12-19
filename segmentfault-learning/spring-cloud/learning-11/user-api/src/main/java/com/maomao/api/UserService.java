package com.maomao.api;

import com.maomao.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by maomao on 2018/12/18.
 */
@FeignClient(name = "${user.service.name}")
public interface UserService {

    @PostMapping("/user/save")
    boolean save(User user);

    @GetMapping("/user/find/all")
    List<User> getAll();

}
