package com.liangcha.system.controller;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.framework.log.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

import static com.liangcha.common.pojo.CommonResult.success;

/**
 * @author 凉茶
 */
@Api(tags = "测试各种功能")
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/null")
    @ApiOperation("忘记测试什么了")
    @Log
    public CommonResult<String> test() {
        System.out.println("成功");
        return CommonResult.success("666");
    }

    @PostMapping("/send")
    @ApiOperation("测试发送短信")
    public CommonResult<Boolean> sendLoginSmsCode() {

        String msg = "1234&1";
        String[] split = msg.split("&");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < split.length; i++) {
            map.put(String.valueOf(i), split[i]);
        }

        SmsBlend smsBlend = SmsFactory.createSmsBlend(SupplierType.TENCENT);
        smsBlend.sendMessage("19923209856", "1978630", map);


        return success(true);
    }
}
