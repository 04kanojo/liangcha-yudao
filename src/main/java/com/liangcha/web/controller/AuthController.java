package com.liangcha.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("管理后台认证")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    @ApiOperation("管理员登录")
    public String login() {
        return "success";
    }

}
