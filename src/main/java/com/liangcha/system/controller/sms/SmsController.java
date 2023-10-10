package com.liangcha.system.controller.sms;

import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.service.sms.SmsCodeService;
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
@Api("短信操作api")
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Resource
    private SmsCodeService smsCodeService;

    @PostMapping("/send-sms-code")
    @ApiOperation("发送手机验证码")
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO, HttpServletRequest request) {
        smsCodeService.sendSmsCode(reqVO, request);
        return success(true);
    }
}
