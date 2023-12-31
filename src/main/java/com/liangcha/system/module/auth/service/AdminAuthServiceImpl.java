package com.liangcha.system.module.auth.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.common.utils.ServletUtils;
import com.liangcha.common.utils.TracerUtils;
import com.liangcha.framework.captcha.CaptchaProperties;
import com.liangcha.framework.convert.auth.AuthConvert;
import com.liangcha.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.system.controller.auth.vo.AuthSmsLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.module.auth.domain.AdminUserDO;
import com.liangcha.system.module.auth2.pojo.LoginUser;
import com.liangcha.system.module.auth2.service.OAuth2TokenService;
import com.liangcha.system.module.log.dto.LoginLogCreateReqDTO;
import com.liangcha.system.module.log.service.LoginLogService;
import com.liangcha.system.module.sms.service.SmsService;
import com.liangcha.system.module.user.enums.LoginLogTypeEnum;
import com.liangcha.system.module.user.enums.LoginResultEnum;
import com.liangcha.system.module.user.enums.UserTypeEnum;
import com.liangcha.system.module.user.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.common.utils.ServletUtils.getClientIP;
import static com.liangcha.common.utils.ServletUtils.getRequest;
import static com.liangcha.system.module.auth2.enums.OAuth2ClientConstants.CLIENT_ID_DEFAULT;

/**
 * @author 凉茶
 */
@Service
//@SuppressWarnings("all")
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Resource
    private AdminUserService userService;

    @Resource
    private CaptchaProperties captchaProperties;

    @Resource
    private SmsService smsService;

    @Resource
    private LoginLogService loginLogService;

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 校验验证码
        EnableCaptcha(reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 创建Token令牌 并 插入登录日志
        return createTokenAfterLoginSuccess(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    private void EnableCaptcha(AuthLoginReqVO reqVO) {
        if (captchaProperties.getEnable()) {
            String userInput = reqVO.getCaptchaVerification();
            String captcha = (String) getRequest().getSession().getAttribute("captcha");
            if (StrUtil.isEmpty(captcha)) {
                createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_EXPIRED);
                throw exception(CAPTCHA_EXPIRED);
            }
            if (!userInput.equals(captcha)) {
                createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
                throw exception(CAPTCHA_ERR);
            }
        }
    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        oauth2TokenService.removeToken(token, CLIENT_ID_DEFAULT);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        LoginUser user = oauth2TokenService.refreshToken(refreshToken, CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(user);
    }

    @Override
    public AuthLoginRespVO smsLogin(AuthSmsLoginReqVO reqVO) {
        // 校验验证码
        smsService.useSmsCode(reqVO.getCode());

        // 获得用户信息
        AdminUserDO user = userService.getByMobile(reqVO.getMobile());
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getMobile(), LoginLogTypeEnum.LOGIN_MOBILE);
    }

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        // 登录场景，验证是否存在
        if (userService.getByMobile(reqVO.getMobile()) == null) {
            throw exception(AUTH_MOBILE_NOT_EXISTS);
        }

        smsService.sendSmsCode(AuthConvert.INSTANCE.convert(reqVO).setCreateIp(getClientIP()));
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

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        LoginUser user = oauth2TokenService.createAccessToken(userId, CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(user);
    }

    private void createLoginLog(Long userId, String username, LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(UserTypeEnum.ADMIN.getCode());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }
}
