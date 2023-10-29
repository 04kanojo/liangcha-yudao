package com.liangcha.framework.security.utils;

import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.security.pojo.LoginUser;
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

    /**
     * 从请求中，获得认证 Token
     *
     * @param request 请求
     * @param header  认证 Token 对应的 Header 名字
     * @return 认证 Token
     */
    public static String getToken(HttpServletRequest request, String header, String bearer) {
        String authorization = request.getHeader(header);
        //判断token是否不合法（为空或者为空字符串）
        if (StrUtil.isBlank(authorization)) {
            return null;
        }
        return StrUtil.subAfter(authorization, bearer, false);
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
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        // 可能有时候并不是此类型,假如为String,转换就会报错,提前判断是否是LoginUser
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
    }

    /**
     * 获取当前用户类型
     *
     * @return 当前用户类型
     */
    public static Integer getUserType() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.getUserType() : null;
    }

    /**
     * 获得当前用户的编号，从上下文中
     *
     * @return 用户编号
     */
    public static Long getLoginUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.getUserId() : null;
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
