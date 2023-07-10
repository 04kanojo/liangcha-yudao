package com.liangcha.controller.auth;

import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.service.auth.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("管理后台认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AdminAuthService adminAuthService;

    @GetMapping("/login")
    @ApiOperation("管理员登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO authLoginReqVO) {
        return CommonResult.success(adminAuthService.login(authLoginReqVO));
    }

}
