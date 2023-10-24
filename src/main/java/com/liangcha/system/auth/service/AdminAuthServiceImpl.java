package com.liangcha.system.auth.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.framework.captcha.CaptchaProperties;
import com.liangcha.framework.convert.auth.AuthConvert;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
import com.liangcha.server.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.server.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.server.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.sms.service.SmsCodeService;
import com.liangcha.system.user.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

/**
 * 凉茶
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Resource
    private AdminUserService userService;

    @Resource
    private CaptchaProperties captchaProperties;

    @Resource
    private SmsCodeService smsCodeService;

    @Override
    public AuthLoginRespVO login(HttpServletRequest request, AuthLoginReqVO reqVO) {
        // 判断验证码
        EnableCaptcha(request, reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 创建 Token 令牌
        return createTokenAfterLoginSuccess(user.getId());
    }

    private void EnableCaptcha(HttpServletRequest request, AuthLoginReqVO reqVO) {
        if (captchaProperties.getEnable()) {
            String userInput = reqVO.getCaptchaVerification();
            String captcha = (String) request.getSession().getAttribute("captcha");
            if (StrUtil.isEmpty(captcha)) {
                throw exception(CAPTCHA_EXPIRED);
            }
            if (!userInput.equals(captcha)) {
                throw exception(CAPTCHA_ERR);
            }
        }
    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        oauth2TokenService.removeAccessToken(token);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        LoginUser user = oauth2TokenService.refreshToken(refreshToken);
        return AuthConvert.INSTANCE.convert(user);
    }

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        // 登录场景，验证是否存在
        if (userService.getByMobile(reqVO.getMobile()) == null) {
            throw exception(AUTH_MOBILE_NOT_EXISTS);
        }

        smsCodeService.sendSmsCode(reqVO);
    }

    //======================================== 功能方法(非重写) ========================================

    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    public AdminUserDO authenticate(String username, String password) {
        // 校验账号是否存在
        AdminUserDO user = userService.getByUsername(username);
        if (user == null) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, HttpServletRequest request) {
        // 创建访问令牌
        LoginUser user = oauth2TokenService.createAccessToken(userId);
        //设置到上下文对象
        SecurityFrameworkUtils.setLoginUser(user, request);
        return AuthConvert.INSTANCE.convert(user);
    }

}
