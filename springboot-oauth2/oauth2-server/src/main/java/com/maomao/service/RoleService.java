package com.maomao.service;

import com.maomao.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findByUserId(Long userId);

    void addRoleResources(Long roleId, List<Long> resourcesIds);

    void removeRoleResources(Long roleId, List<Long> resourcesIds);
}
