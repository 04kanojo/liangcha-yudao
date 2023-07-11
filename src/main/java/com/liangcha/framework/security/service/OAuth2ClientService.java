package com.liangcha.framework.security.service;


import com.liangcha.framework.security.pojo.domain.OAuth2ClientDO;
import com.liangcha.framework.security.pojo.vo.OAuth2ClientCreateReqVO;
import com.liangcha.framework.security.pojo.vo.OAuth2ClientUpdateReqVO;

import javax.validation.Valid;
import java.util.Collection;

/**
 * OAuth2.0 Client Service 接口
 * <p>
 * 从功能上，和 JdbcClientDetailsService 的功能，提供客户端的操作
 *
 * @author 凉茶
 */
public interface OAuth2ClientService {

    /**
     * 初始化 OAuth2Client 的本地缓存
     */
    void initLocalCache();

    /**
     * 创建 OAuth2 客户端
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOAuth2Client(@Valid OAuth2ClientCreateReqVO createReqVO);

    /**
     * 更新 OAuth2 客户端
     *
     * @param updateReqVO 更新信息
     */
    void updateOAuth2Client(@Valid OAuth2ClientUpdateReqVO updateReqVO);

    /**
     * 删除 OAuth2 客户端
     *
     * @param id 编号
     */
    void deleteOAuth2Client(Long id);

    /**
     * 获得 OAuth2 客户端
     *
     * @param id 编号
     * @return OAuth2 客户端
     */
    OAuth2ClientDO getOAuth2Client(Long id);


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
    OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret,
                                             String authorizedGrantType, Collection<String> scopes, String redirectUri);

}