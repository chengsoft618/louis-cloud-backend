package com.louis.server.service;

import com.louis.core.service.ICRUDService;
import com.louis.oauth.dto.RoleDto;
import com.louis.oauth.dto.UserRoleDto;
import com.louis.server.entity.SysRole;
import com.louis.server.entity.SysUser;
import com.louis.server.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/6/29
 * description:
 */

public interface UserRoleService extends ICRUDService<UserRole, Long> {

    List<UserRole> findByUserId(long userId);

    List<UserRole> findByRoleId(long roleId);

    void blindRole(UserRoleDto dto);

    List<SysRole> getRoleByUser(long userId);

    void updateUserRole(long userId, String oldRoleName, RoleDto roleDto);



}
