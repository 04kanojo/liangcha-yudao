package com.liangcha.system.auth.service;

import com.liangcha.server.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.server.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.server.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.auth.domain.AdminUserDO;

import javax.validation.Valid;

/**
 * @author 凉茶
 */
public interface AdminAuthService {

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     */
    void logout(String token);

    /**
     * 刷新访问令牌
     * <p>
     *
     * @param refreshToken 刷新令牌
     * @return 访问令牌的信息
     */
    AuthLoginRespVO refreshToken(String refreshToken);

    /**
     * @param reqVO 请求信息
     */
    void sendSmsCode(AuthSmsSendReqVO reqVO);

    /**
     * 根据用户名和密码校验用户
     */
    AdminUserDO authenticate(String username, String password);
}
