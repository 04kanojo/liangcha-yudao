package com.liangcha.framework.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.liangcha.framework.common.enums.ErrorCodeEnum;
import com.liangcha.framework.common.utils.SecurityFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 访问一个需要认证的 URL 资源，已经认证（登录）但是没有权限的情况
 *
 * @author 凉茶
 */
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.error("[用户({}) 访问 URL({}) 时，权限不够]", SecurityFrameworkUtils.getLoginUserId(), request.getRequestURI(), e);
        ServletUtil.write(response, JSONUtil.toJsonStr(ErrorCodeEnum.SMALL_AUTHORITY), MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

}
