package com.liangcha.common.utils;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 客户端工具类
 *
 * @author 凉茶
 */
public class ServletUtils {

    /**
     * 返回 JSON 字符串
     *
     * @param response 响应
     * @param object   对象，会序列化成 JSON 字符串
     */
    @SuppressWarnings("deprecation") // 必须使用 APPLICATION_JSON_UTF8_VALUE，否则会乱码
    public static void writeJSON(HttpServletResponse response, Object object) {
        String content = JSON.toJSONString(object);
        ServletUtil.write(response, content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    /**
     * 获得请求
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获得请求
     *
     * @return HttpServletRequest
     */
    public static Object getAttribute(String key) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            HttpSession session = request.getSession();
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * 获得请求ip
     */
    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return ServletUtil.getClientIP(request);
    }

    /**
     * 获取用户代理
     */
    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }
}
