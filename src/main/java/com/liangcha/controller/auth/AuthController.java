package com.liangcha.controller.auth;

import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.service.auth.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 凉茶
 */
@Api("管理后台认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AdminAuthService adminAuthService;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public CommonResult<AuthLoginRespVO> login(HttpServletRequest request, @RequestBody @Valid AuthLoginReqVO authLoginReqVO) {
        return CommonResult.success(adminAuthService.login(request, authLoginReqVO));
    }
}
