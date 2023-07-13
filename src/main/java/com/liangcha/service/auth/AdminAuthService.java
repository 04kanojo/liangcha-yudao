package com.liangcha.service.auth;

import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.domain.auth.AdminUserDO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 凉茶
 */
public interface AdminAuthService {
    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(HttpServletRequest request, @Valid AuthLoginReqVO reqVO);
}
