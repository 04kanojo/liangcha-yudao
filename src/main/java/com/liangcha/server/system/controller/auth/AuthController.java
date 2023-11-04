package com.liangcha.server.system.controller.auth;

import cn.hutool.core.util.StrUtil;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.framework.log.annotation.Log;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
import com.liangcha.server.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.server.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.server.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.auth.service.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.liangcha.common.pojo.CommonResult.success;

/**
 * @author 凉茶
 */
@Api("管理后台认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO authLoginReqVO) {
        return success(authService.login(authLoginReqVO));
    }

    @PostMapping("/logout")
    @ApiOperation("登出系统")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.getToken(request, securityProperties.getTokenHeader(), securityProperties.getAuthorizationBearer());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return success(true);
    }

    @Log
    @PostMapping("/refresh-token")
    @ApiOperation("刷新令牌")
    public CommonResult<AuthLoginRespVO> refreshToken(String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    @PostMapping("/send-sms-code")
    @ApiOperation("发送手机验证码")
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(reqVO);
        return success(true);
    }
}
