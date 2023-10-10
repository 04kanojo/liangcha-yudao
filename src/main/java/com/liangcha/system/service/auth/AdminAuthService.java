package com.liangcha.system.service.auth;

import com.liangcha.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 凉茶
 */
public interface AdminAuthService {

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(HttpServletRequest request, @Valid AuthLoginReqVO reqVO);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     */
    void logout(String token);

}
