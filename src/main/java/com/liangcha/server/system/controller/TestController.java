package com.liangcha.server.system.controller;

import com.liangcha.common.enums.ErrorCodeEnum;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.common.utils.ServiceExceptionUtil;
import com.liangcha.framework.log.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "测试各种功能")
@RestController
@RequestMapping
public class TestController {
    @GetMapping("/test")
    @ApiOperation("test")
    @Log
    public CommonResult<String> test(HttpServletRequest request, @RequestParam("msg") String msg) {
        if (true) {
            throw ServiceExceptionUtil.exception(ErrorCodeEnum.ERR_REQUEST);
        }
        return CommonResult.success(msg);
    }
}
