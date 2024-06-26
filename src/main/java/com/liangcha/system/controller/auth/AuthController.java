package com.liangcha.system.controller.auth;

import cn.hutool.core.util.StrUtil;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
import com.liangcha.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.system.controller.auth.vo.AuthSmsLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.module.auth.service.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.liangcha.common.pojo.CommonResult.success;

/**
 * @author 凉茶
 */
@Api(tags = "管理后台 - 登录认证")
@RestController
@RequestMapping("/system/auth")
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
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

    @PostMapping("/refresh-token")
    @ApiOperation("刷新令牌")
    public CommonResult<AuthLoginRespVO> refreshToken(String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    // 套餐已过期
    @PostMapping("/send-sms-code")
    @ApiOperation("发送手机验证码")
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(reqVO);
        return success(true);
    }

    @PostMapping("/sms-login")
    @PermitAll
    @ApiOperation("使用短信验证码登录")
    public CommonResult<AuthLoginRespVO> smsLogin(@RequestBody @Valid AuthSmsLoginReqVO reqVO) {
        return success(authService.smsLogin(reqVO));
    }
}
