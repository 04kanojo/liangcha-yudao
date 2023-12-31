package com.liangcha.framework.security.handler;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.liangcha.common.enums.ErrorCodeEnum.NO_LOGIN;

/**
 * 访问一个需要认证的 URL 资源，但是此时自己尚未认证（登录）的情况
 *
 * @author 凉茶
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error("[访问 URL({}) 时，没有登录]", request.getRequestURI(), e);
        ServletUtils.writeJSON(response, CommonResult.error(NO_LOGIN));
    }
}
