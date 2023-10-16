package com.liangcha.system.service.user;

import com.liangcha.system.domain.auth.AdminUserDO;

/**
 * 后台用户 Service 接口
 *
 * @author 凉茶
 */
public interface AdminUserService {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getByUsername(String username);

    /**
     * 通过用户id查询用户
     *
     * @param id 用户id
     * @return 用户对象信息
     */
    AdminUserDO getById(Long id);

    /**
     * 通过手机号查询用户
     *
     * @param mobile 手机号
     * @return 用户对象信息
     */
    AdminUserDO getByMobile(String mobile);


    /**
     * 判断密码是否匹配
     *
     * @param rawPassword     未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);

}
