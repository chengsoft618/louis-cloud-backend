package com.louis.server.service.impl;

import com.louis.common.api.dto.LoginAuthDto;
import com.louis.core.redis.RedisOperate;
import com.louis.core.service.CRUDService;
import com.louis.oauth.dto.ClientMessageDto;
import com.louis.oauth.dto.ModifyPswDto;
import com.louis.security.core.SecurityUser;
import com.louis.constant.RedisConstant;
import com.louis.server.entity.SysUser;
import com.louis.server.repository.SysUserRepository;
import com.louis.server.service.PasswordService;
import com.louis.server.service.SysUserService;
import com.louis.server.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Eric
 * @date create in 2019/4/15
 */
@Service
@Slf4j
public class SysUserServiceImpl extends CRUDService<SysUser, Long> implements SysUserService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private  SysUserRepository sysUserRepository;

    @Autowired
    private  RedisOperate<SysUser> redisOperate;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    ClientMessageService clientMessageService;


    @Autowired
    RedisTemplate<Object ,Object> redisTemplate;



    public SysUser findByUserName(String userName) {

        log.info(" find user by user name ; username:{}", userName);
//        SysUser user =  getUserFromRedisCache(RedisConstant.SYS_USER + userName);
        SysUser user = null;
        if (user == null) {
            user = sysUserRepository.findByUsername(userName);
            if (user!=null) {
//                putUserToRedisCache(user);
            }
        }
        return user;
    }

    @Override
    public void modifyPsw(ModifyPswDto modifyPswDto, LoginAuthDto loginAuthDto) {
        String loginName = modifyPswDto.getLoginName();
        SysUser sysUser = findByUserName(loginName);
        String salt = sysUser.getSalt();
        String newPassword = passwordService.modifyPsw(modifyPswDto, loginAuthDto, salt);
        sysUser.setPassword(newPassword);
         save(sysUser);

    }


    @Override
    public SysUser save(SysUser user) {
        if (user.getRegistryDate()==null) {
            user.setRegistryDate(new Date());
        }
        if (user.getIdentityNumber()==null) {
            user.setIdentityNumber(user.getUsername());
        }
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));
        return super.save(user);
    }

    public void putUserToRedisCache(SysUser user) {
        try {
            if( user == null ) return;
            String key = RedisConstant.SYS_USER+user.getIdToString();
            redisOperate.set(key, user, 12);
            redisTemplate.opsForValue().set(RedisConstant.SYS_USER+user.getUsername(), key, 12);
            redisTemplate.opsForValue().set(RedisConstant.SYS_USER+user.getEmail(), key, 12);
            redisTemplate.opsForValue().set(RedisConstant.SYS_USER+user.getPhone(), key, 12);
        }catch(Exception e) {
            log.error("save user to redis error:{}", e.getMessage());
        }
    }

    public SysUser getUserFromRedisCache(String key) {
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            if( obj instanceof SysUser)
                return (SysUser)obj;

            //username,phone,email,empid
            if( obj instanceof String) {
                Object obj2 = redisTemplate.opsForValue().get(String.valueOf(obj));
                if( obj2 instanceof SysUser)
                    return (SysUser)obj2;
                if( obj2 == null ) {
                    redisTemplate.delete(String.valueOf(obj));
                }
            }
        }catch(Exception e) {

            log.error("get user from redis error: key :{},{}", key, e.getMessage());
        }
        return null;
    }





    /**
     * 处理登录信息
     * @param token
     * @param principal
     * @param request
     */
    public void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request) {
        log.info("handle login data ;  token:{}", token);
        ClientMessageDto messageDto = clientMessageService.findClientMessage(request);

        Long userId = principal.getUserId();
        LoginAuthDto loginAuthDto = new LoginAuthDto(userId, principal.getLoginName(), principal.getNickName(), principal.getGroupId(), principal.getGroupName());
        // 记录token日志

        userTokenService.saveUserToken(token,loginAuthDto);
        // 记录操作日志
        taskExecutor.execute(() -> loginLogService.saveLoginLog(messageDto,loginAuthDto));
    }




}