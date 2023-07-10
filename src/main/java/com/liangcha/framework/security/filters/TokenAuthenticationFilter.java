package com.liangcha.framework.security.filters;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.common.handler.GlobalExceptionHandler;
import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.framework.common.util.WebFrameworkUtils;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.framework.security.util.SecurityFrameworkUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token 过滤器，验证 token 的有效性
 *
 * @author 凉茶
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private GlobalExceptionHandler globalExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = SecurityFrameworkUtils.getToken(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            Integer userType = WebFrameworkUtils.getLoginUserType(request);
            try {
                // 1.1 基于 token 构建登录用户
                LoginUser loginUser = buildLoginUserByToken(token, userType);
                // 1.2 模拟 Login 功能，方便日常开发调试
//                if (loginUser == null) {
//                    loginUser = mockLoginUser(request, token, userType);
//                }
                // 2. 设置当前用户
                if (loginUser != null) {
                    SecurityFrameworkUtils.setLoginUser(loginUser, request);
                }
            } catch (Throwable ex) {
                CommonResult<?> commonResult = globalExceptionHandler.tokenFilterExceptionHandler(ex);
                ServletUtil.write(response, JSONUtil.toJsonStr(commonResult), MediaType.APPLICATION_JSON_UTF8_VALUE);
                return;
            }
        }
        // 继续过滤链
        chain.doFilter(request, response);
    }

    private LoginUser buildLoginUserByToken(String token, Integer userType) {
        try {
            OAuth2AccessTokenDO accessToken = oauth2TokenService.checkAccessToken(token);
            if (accessToken == null) {
                return null;
            }
            // 用户类型不匹配，无权限
            if (ObjectUtil.notEqual(accessToken.getUserType(), userType)) {
                throw new AccessDeniedException("错误的用户类型");
            }
            // 构建登录用户
            return LoginUser.builder().id(accessToken.getUserId()).userType(accessToken.getUserType()).tenantId(accessToken.getTenantId()).scopes(accessToken.getScopes()).build();
        } catch (ServiceException serviceException) {
            return null;
        }
    }

    /**
     * 模拟登录用户，方便日常开发调试
     * <p>
     * 注意，在线上环境下，一定要关闭该功能！！！
     *
     * @param request  请求
     * @param token    模拟的 token，格式为 {@link SecurityProperties#getMockSecret()} + 用户编号
     * @param userType 用户类型
     * @return 模拟的 LoginUser
     */
    private LoginUser mockLoginUser(HttpServletRequest request, String token, Integer userType) {
        if (!securityProperties.getMockEnable()) {
            return null;
        }
        // 必须以 mockSecret 开头
        if (!token.startsWith(securityProperties.getMockSecret())) {
            return null;
        }
        // 构建模拟用户
        Long userId = Long.valueOf(token.substring(securityProperties.getMockSecret().length()));
        return LoginUser.builder().id(userId).userType(userType).tenantId(WebFrameworkUtils.getTenantId(request)).build();
    }

}
