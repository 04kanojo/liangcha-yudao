package com.liangcha.system.module.auth2.service;


import com.liangcha.system.module.auth2.pojo.domain.OAuth2ClientDO;

import java.util.Collection;
import java.util.List;

/**
 * OAuth2.0 Client Service 接口
 * <p>
 * 从功能上，和 JdbcClientDetailsService 的功能，提供客户端的操作
 *
 * @author 凉茶
 */
public interface OAuth2ClientService {
    /**
     * 从缓存中，校验客户端是否合法
     * <p>
     * 非空时，进行校验
     *
     * @param clientId            客户端编号
     * @param clientSecret        客户端密钥
     * @param authorizedGrantType 授权方式
     * @param scopes              授权范围
     * @param redirectUri         重定向地址
     * @return 客户端
     */
    OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String authorizedGrantType, Collection<String> scopes, String redirectUri);

    /**
     * 部分地方只需要获取客户端,跳转作用
     *
     * @param clientId 客户端编号
     * @return 客户端
     */
    default OAuth2ClientDO validOAuthClientFromCache(String clientId) {
        return validOAuthClientFromCache(clientId, null, null, null, null);
    }

    /**
     * 获取全部客户端
     * TODO 目前只用于初始化
     *
     * @return 客户端全部数据
     */
    List<OAuth2ClientDO> getAll();
}
