package com.liangcha.security.util;

import cn.hutool.core.util.StrUtil;
import com.liangcha.security.LoginUser;
import com.liangcha.security.config.SecurityProperties;
import com.liangcha.web.WebFrameworkUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * 安全服务工具类
 *
 * @author 凉茶
 */
public class SecurityFrameworkUtils {
    public static SecurityProperties properties;

    private SecurityFrameworkUtils() {
    }

    /**
     * 从请求中，获得认证 Token
     *
     * @param request 请求
     * @param header  认证 Token 对应的 Header 名字
     * @return 认证 Token
     */
    public static String getToken(HttpServletRequest request, String header) {
        String authorization = request.getHeader(header);
        //判断token是否不合法（为空或者为空字符串）
        if (StrUtil.isBlank(authorization)) {
            return null;
        }
        String tokenPrefix = properties.getAuthorizationBearer() + " ";
        int index = authorization.indexOf(tokenPrefix);
        // 未找到
        if (index == -1) {
            return null;
        }
        return authorization.substring(index + tokenPrefix.length()).trim();
    }

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    @Nullable
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
    }

    /**
     * 获得当前用户的编号，从上下文中
     *
     * @return 用户编号
     */
    @Nullable
    public static Long getLoginUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

    /**
     * 设置当前用户
     *
     * @param loginUser 登录用户
     * @param request   请求
     */
    public static void setLoginUser(LoginUser loginUser, HttpServletRequest request) {
        // 创建 Authentication，并设置到上下文
        Authentication authentication = buildAuthentication(loginUser, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 额外设置到 request 中，用于 ApiAccessLogFilter 可以获取到用户编号；
        // 原因是，Spring Security 的 Filter 在 ApiAccessLogFilter 后面，在它记录访问日志时，线上上下文已经没有用户编号等信息
        //TODO security配置过滤器链顺序应该跨域解决
        WebFrameworkUtils.setLoginUserId(request, loginUser.getId());
        WebFrameworkUtils.setLoginUserType(request, loginUser.getUserType());
    }

    /**
     * 创建 UsernamePasswordAuthenticationToken 对象,此对象是Authentication的子类,他才有setDetails
     *
     * @param loginUser 当前登录用户
     * @param request   请求
     * @return 子类
     */
    private static Authentication buildAuthentication(LoginUser loginUser, HttpServletRequest request) {
        // 创建 UsernamePasswordAuthenticationToken 对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser, null, Collections.emptyList());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }

}
