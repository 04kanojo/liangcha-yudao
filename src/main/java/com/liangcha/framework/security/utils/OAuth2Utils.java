package com.liangcha.framework.security.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

/**
 * OAuth2 相关的工具类
 *
 * @author 芋道源码
 */
public class OAuth2Utils {

    public static List<String> buildScopes(String scope) {
        return StrUtil.split(scope, ' ');
    }

    /**
     * 获取客户端id和密钥
     * 获取顺序:请求头,url
     *
     * @return 客户端id和密钥的字符串数组
     */
    public static String[] getBasicAuthorization(HttpServletRequest request) {
        String clientId;
        String clientSecret;
        // 先从 Header 中获取
        String authorization = request.getHeader("Authorization");
        authorization = StrUtil.subAfter(authorization, "Basic ", true);

        if (StrUtil.isBlank(authorization)) {
            // 将得到的字符串解码得到客户端信息,格式: clientId:clientSecret
            authorization = Base64.decodeStr(authorization);
            clientId = StrUtil.subBefore(authorization, ":", false);
            clientSecret = StrUtil.subAfter(authorization, ":", false);
        } else {
            //从url中获取
            clientId = request.getParameter("client_id");
            clientSecret = request.getParameter("client_secret");
        }

        // 如果两者非空，则返回
        if (StrUtil.isNotEmpty(clientId) && StrUtil.isNotEmpty(clientSecret)) {
            return new String[]{clientId, clientSecret};
        }
        return null;
    }
}
