package com.liangcha.framework.security.filters;

import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
import com.liangcha.system.module.auth2.pojo.LoginUser;
import com.liangcha.system.module.auth2.service.OAuth2TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.liangcha.system.module.auth2.enums.OAuth2ClientConstants.CLIENT_ID_DEFAULT;

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
    private Cache<String, String> allTokenCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = SecurityFrameworkUtils.getToken(request, securityProperties.getTokenHeader(), securityProperties.getAuthorizationBearer());
        if (StrUtil.isNotBlank(token)) {
            // 1.1 获取到client_id
            String clientId = allTokenCache.get(token);
            // 1.2 基于 client_id 和 token 构建登录用户
            LoginUser loginUser = oauth2TokenService.getUserByAccessToken(token, clientId == null ? CLIENT_ID_DEFAULT : clientId);

            // 2. 设置当前用户
            if (loginUser != null) {
                SecurityFrameworkUtils.setLoginUser(loginUser, request);
            }
        }
        // 继续过滤链
        chain.doFilter(request, response);
    }

}
