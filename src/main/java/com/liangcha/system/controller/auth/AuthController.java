package com.liangcha.system.controller.auth;

import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.system.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.system.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.system.service.auth.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.liangcha.framework.common.pojo.CommonResult.success;

/**
 * @author 凉茶
 */
@Api("管理后台认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public CommonResult<AuthLoginRespVO> login(HttpServletRequest request, @RequestBody @Valid AuthLoginReqVO authLoginReqVO) {
        return success(authService.login(request, authLoginReqVO));
    }

//    @PostMapping("/social-login")
//    @ApiOperation("社交快捷登录，使用 code 授权码")
//    public CommonResult<AuthLoginRespVO> socialQuickLogin(@RequestBody @Valid AuthSocialLoginReqVO reqVO) {
//        return success(authService.socialLogin(reqVO));
//    }
}
