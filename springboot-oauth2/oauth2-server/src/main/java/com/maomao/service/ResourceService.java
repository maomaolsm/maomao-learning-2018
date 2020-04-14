package com.maomao.service;

import com.maomao.domain.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> findByUserId(Long userId);

    List<Resource> findByRoleId(Long roleId);
}
