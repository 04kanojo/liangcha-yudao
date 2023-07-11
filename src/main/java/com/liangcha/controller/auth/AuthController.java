package com.liangcha.controller.auth;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.liangcha.controller.auth.vo.AuthLoginReqVO;
import com.liangcha.controller.auth.vo.AuthLoginRespVO;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.service.auth.AdminAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Resource
    private CaptchaService captchaService;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO authLoginReqVO) {
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(authLoginReqVO.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        //验证码错误
        if (!response.isSuccess()) {
            throw new ServiceException(Integer.parseInt(response.getRepCode()), response.getRepMsg());
        }
        return CommonResult.success(adminAuthService.login(authLoginReqVO));
    }

}
