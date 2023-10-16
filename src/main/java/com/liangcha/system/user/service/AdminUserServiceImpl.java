package com.liangcha.system.user.service;

import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.user.dao.AdminUserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 后台用户 Service 实现类
 *
 * @author 凉茶
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminUserDO getByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public AdminUserDO getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public AdminUserDO getByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
