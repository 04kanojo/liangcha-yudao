package com.liangcha.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("test")
@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("@ss.hasPermission('system:user:test')")
    @GetMapping
    @ApiOperation("管理员登录")
    public String login() {
        return "success";
    }
}
