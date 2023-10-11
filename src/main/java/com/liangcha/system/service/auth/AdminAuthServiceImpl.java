package com.liangcha.system.service.auth;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.captcha.CaptchaProperties;
import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.common.enums.UserTypeEnum;
import com.liangcha.framework.convert.auth.AuthConvert;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.system.domain.auth.AdminUserDO;
import com.liangcha.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.*;
import static com.liangcha.framework.common.utils.ServiceExceptionUtil.exception;

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

    @Override
    public AuthLoginRespVO login(HttpServletRequest request, AuthLoginReqVO reqVO) {
        // 开启验证码执行
        if (captchaProperties.getCaptcha()) {
            String userInput = reqVO.getCaptchaVerification();
            String captcha = (String) request.getSession().getAttribute("captcha");
            if (StrUtil.isEmpty(captcha)) {
                throw exception(CAPTCHA_EXPIRED);
            }
            if (!userInput.equals(captcha)) {
                throw exception(CAPTCHA_ERR);
            }
        }

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 创建 Token 令牌
        return createTokenAfterLoginSuccess(user.getId());
    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        oauth2TokenService.removeAccessToken(token);
    }

    @Override
    public OAuth2AccessTokenDO refreshToken(String refreshToken) {
        return oauth2TokenService.refreshToken(refreshToken);
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
        AdminUserDO user = userService.getUserByUsername(username);
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

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId) {
        OAuth2AccessTokenCreateReqDTO oAuth2AccessTokenCreateReqDTO = OAuth2AccessTokenCreateReqDTO.builder().userId(userId).userType(UserTypeEnum.ADMIN.getCode()).build();
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(oAuth2AccessTokenCreateReqDTO);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

}
