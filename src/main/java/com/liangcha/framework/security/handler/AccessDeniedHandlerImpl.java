package com.liangcha.framework.security.handler;

import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.framework.common.utils.SecurityFrameworkUtils;
import com.liangcha.framework.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.SMALL_AUTHORITY;


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
        ServletUtils.writeJSON(response, CommonResult.error(SMALL_AUTHORITY));
    }

}
