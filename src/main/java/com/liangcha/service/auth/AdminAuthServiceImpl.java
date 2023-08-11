package com.liangcha.service.auth;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.controller.auth.vo.AuthSocialLoginReqVO;
import com.liangcha.convert.auth.AuthConvert;
import com.liangcha.domain.auth.AdminUserDO;
import com.liangcha.framework.common.captcha.CaptchaProperties;
import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.common.enums.ErrorCodeEnum;
import com.liangcha.framework.common.enums.UserTypeEnum;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.service.social.SocialUserService;
import com.liangcha.service.user.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.AUTH_THIRD_LOGIN_NOT_BIND;
import static com.liangcha.framework.common.enums.ErrorCodeEnum.USER_NOT_EXISTS;

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
    private SocialUserService socialUserService;

    @Override
    public AuthLoginRespVO login(HttpServletRequest request, AuthLoginReqVO reqVO) {
        // 开启验证码执行
        if (captchaProperties.getCaptcha()) {
            String userInput = reqVO.getCaptchaVerification();
            String captcha = (String) request.getSession().getAttribute("captcha");
            if (StrUtil.isEmpty(captcha)) {
                throw new ServiceException(ErrorCodeEnum.CAPTCHA_EXPIRED);
            }
            if (!userInput.equals(captcha)) {
                throw new ServiceException(ErrorCodeEnum.CAPTCHA_ERR);
            }
        }

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 如果 socialType 非空，说明需要绑定社交用户
//        if (reqVO.getSocialType() != null) {
//            socialUserService.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType().getValue(),
//                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
//        }
        // 创建 Token 令牌
        return createTokenAfterLoginSuccess(user.getId());
    }

    @Override
    public AuthLoginRespVO socialLogin(AuthSocialLoginReqVO reqVO) {
//        // 使用 code 授权码，进行登录。然后，获得到绑定的用户编号
//        Long userId = socialUserService.getBindUserId(UserTypeEnum.ADMIN.getCode(),
//                reqVO.getType(), reqVO.getCode(), reqVO.getState());
//        if (userId == null) {
//            throw new ServiceException(AUTH_THIRD_LOGIN_NOT_BIND);
//        }
//
//        // 获得用户
//        AdminUserDO user = userService.getUser(userId);
//        if (user == null) {
//            throw new ServiceException(USER_NOT_EXISTS);
//        }
//
//        // 创建 Token 令牌，记录登录日志
//        return createTokenAfterLoginSuccess(user.getId());
        return null;
    }

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
            throw new ServiceException(ErrorCodeEnum.AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw new ServiceException(ErrorCodeEnum.AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw new ServiceException(ErrorCodeEnum.AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId) {
        // 创建访问令牌
        OAuth2AccessTokenCreateReqDTO oAuth2AccessTokenCreateReqDTO = OAuth2AccessTokenCreateReqDTO.builder().userId(userId).userType(UserTypeEnum.ADMIN.getCode()).clientId("default").build();
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(oAuth2AccessTokenCreateReqDTO);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

}
