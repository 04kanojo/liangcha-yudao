package com.liangcha.service.auth;

import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.controller.auth.vo.AuthSocialLoginReqVO;

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
     * 社交快捷登录，使用 code 授权码
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO socialLogin(@Valid AuthSocialLoginReqVO reqVO);
}
