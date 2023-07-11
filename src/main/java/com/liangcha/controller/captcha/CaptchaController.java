//package com.liangcha.controller.captcha;
//
//import com.anji.captcha.model.common.ResponseModel;
//import com.anji.captcha.model.vo.CaptchaVO;
//import com.anji.captcha.service.CaptchaService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * 验证码
// * RestController命名 -> 其他包也有这个bean,注入出错
// *
// * @author 凉茶
// */
//@Api("管理后台 - 验证码")
//@RestController("adminCaptchaController")
//@RequestMapping("/system/captcha")
//public class CaptchaController {
//
//    @Resource
//    private CaptchaService captchaService;
//
//    @PostMapping("/get")
//    @ApiOperation("获得验证码")
//    public ResponseModel get(@RequestBody CaptchaVO data) {
//        return captchaService.get(data);
//    }
//
//    @PostMapping("/check")
//    @ApiOperation("校验验证码")
//    public ResponseModel check(@RequestBody CaptchaVO data) {
//        return captchaService.check(data);
//    }
//
//}
