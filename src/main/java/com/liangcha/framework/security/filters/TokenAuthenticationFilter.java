package com.liangcha.framework.security.filters;

import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.permission.service.PermissionService;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.framework.security.service.OAuth2TokenService;
import com.liangcha.framework.security.utils.SecurityFrameworkUtils;
import com.liangcha.system.domain.permission.RoleDO;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    private PermissionService permissionService;

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

        //token未过期但是刷新过期,重新创建刷新令牌
        if (accessToken.getRefreshToken() == null) {
            oauth2TokenService.createOAuth2RefreshToken(accessToken.getUserId(), accessToken.getUserType());
        }

        //数据权限需要使用角色数组
        List<RoleDO> roles = permissionService.getEnableUserRoleListByUserId(accessToken.getUserId());

        // 构建登录用户
        return new LoginUser()
                .setId(accessToken.getUserId())
                .setUserType(accessToken.getUserType())
                .setRoles(roles);
    }
}
