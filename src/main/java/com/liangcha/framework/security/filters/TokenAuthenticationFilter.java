package com.liangcha.framework.security.filters;

import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = SecurityFrameworkUtils.getToken(request, securityProperties.getTokenHeader(), securityProperties.getAuthorizationBearer());
        if (StrUtil.isNotBlank(token)) {
            // 1.1 基于 token 构建登录用户
            LoginUser loginUser = buildLoginUserByToken(token);
            // 2. 设置当前用户
            if (loginUser != null) {
                SecurityFrameworkUtils.setLoginUser(loginUser, request);
            }
        }
        // 继续过滤链
        chain.doFilter(request, response);
    }

    private LoginUser buildLoginUserByToken(String token) {
        OAuth2AccessTokenDO accessToken = oauth2TokenService.checkAccessToken(token);
        if (accessToken == null) {
            return null;
        }
        // 构建登录用户
        return LoginUser.builder()
                .id(accessToken.getUserId())
                .userType(accessToken.getUserType())
                .build();
    }
}
