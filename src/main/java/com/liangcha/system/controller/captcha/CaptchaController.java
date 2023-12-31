package com.liangcha.system.controller.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import com.liangcha.framework.captcha.CaptchaProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 凉茶
 */
@Api(tags = "管理后台 - 验证码")
@RestController
@RequestMapping("/system/captcha")
public class CaptchaController {

    @Resource
    private CaptchaProperties captchaProperties;

    @ApiOperation("获取验证码")
    @GetMapping("/get")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ServletOutputStream outputStream = response.getOutputStream();
        /*
            干扰线验证码
            width     图片宽
            height    图片高
            codeCount 字符个数
            thickness 干扰线宽度
         */
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式 numberLength 参与计算数据位数
        captcha.setGenerator(new MathGenerator(1));
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        captcha.write(outputStream);
        // 关闭流
        outputStream.close();

        //因为直接输出了图片,确保验证码对应一个用户,存入session
        //不用cookie是因为cookie是存在客户端(浏览器),有泄露风险
        String code = captcha.getCode();
        //将code字符串转为结果（一位数）
        char[] codes = code.toCharArray();
        int i = Integer.parseInt(String.valueOf(codes[0]));
        int j = Integer.parseInt(String.valueOf(codes[2]));

        int result = 0;
        switch (codes[1]) {
            case '*': {
                result = i * j;
                break;
            }
            case '-': {
                result = i - j;
                break;
            }
            case '+': {
                result = i + j;
                break;
            }
        }
        // 使用redis,(key,value)形式,但是前端未登录的情况下,不太好找key值,所以使用session
        // captcha.getImageBase64(); 转为base64传递给前端
        long seconds = captchaProperties.getExpireTimes().getSeconds();
        session.setMaxInactiveInterval(Integer.parseInt(String.valueOf(seconds)));
        session.setAttribute("captcha", Integer.toString(result));
    }
}
