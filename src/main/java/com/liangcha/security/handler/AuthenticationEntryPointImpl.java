package com.liangcha.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.liangcha.common.exception.GlobalErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问一个需要认证的 URL 资源，但是此时自己尚未认证（登录）的情况
 *
 * @author 凉茶
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("[访问 URL({}) 时，没有登录]", request.getRequestURI(), e);
        ServletUtil.write(response, JSONUtil.toJsonStr(GlobalErrorCodeEnum.NO_LOGIN), MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
