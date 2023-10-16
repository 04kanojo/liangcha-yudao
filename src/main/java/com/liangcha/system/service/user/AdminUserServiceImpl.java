package com.liangcha.system.service.user;

import com.liangcha.system.dao.user.AdminUserMapper;
import com.liangcha.system.domain.auth.AdminUserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 后台用户 Service 实现类
 *
 * @author 凉茶
 */
@Service("adminUserService")
@Slf4j
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

    //======================================== 功能方法(非重写) ========================================

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
