package com.liangcha.service.auth;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.convert.auth.AuthConvert;
import com.liangcha.domain.auth.AdminUserDO;
import com.liangcha.framework.common.captcha.CaptchaProperties;
import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.common.enums.ErrorCodeConstants;
import com.liangcha.framework.common.enums.UserTypeEnum;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.pojo.dto.OAuth2AccessTokenCreateReqDTO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.service.user.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Resource
    private AdminUserService userService;

    @Resource
    private CaptchaProperties captchaProperties;

    @Override
    public AdminUserDO authenticate(String username, String password) {
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ServiceException(ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS.getCode(), ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS.getMessage());
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw new ServiceException(ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS.getCode(), ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS.getMessage());
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw new ServiceException(ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED.getCode(), ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED.getMessage());
        }
        return user;
    }

    @Override
    public AuthLoginRespVO login(HttpServletRequest request, AuthLoginReqVO reqVO) {
        // 开启验证码执行
        if (captchaProperties.getCaptcha()) {
            String userInput = reqVO.getCaptchaVerification();
            String captcha = (String) request.getSession().getAttribute("captcha");
            if (StrUtil.isEmpty(captcha)) {
                throw new ServiceException("验证码失效，请重新获取");
            }
            if (!userInput.equals(captcha)) {
                throw new ServiceException("验证码错误");
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

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId) {
        // 创建访问令牌
        OAuth2AccessTokenCreateReqDTO oAuth2AccessTokenCreateReqDTO = OAuth2AccessTokenCreateReqDTO.builder().userId(userId).userType(UserTypeEnum.ADMIN.getCode()).clientId("default").build();
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(oAuth2AccessTokenCreateReqDTO);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }
}
