package com.louis.server.service;


import com.louis.common.api.dto.LoginAuthDto;
import com.louis.core.service.ICRUDService;
import com.louis.oauth.dto.ModifyPswDto;
import com.louis.oauth.dto.RegistryUserDto;
import com.louis.oauth.dto.UserDto;
import com.louis.security.core.SecurityUser;
import com.louis.server.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author John·Louis
 * @date create in 2019/6/29
 * description:
 */
public interface SysUserService extends ICRUDService<SysUser, Long> {


    SysUser findByUserName(String userName);

    void modifyPsw(ModifyPswDto modifyPswDto, LoginAuthDto loginAuthDto);

    void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request);

    SysUser registryUser(RegistryUserDto user);

    Collection<GrantedAuthority> loadUserAuthorities(long userId);




}
