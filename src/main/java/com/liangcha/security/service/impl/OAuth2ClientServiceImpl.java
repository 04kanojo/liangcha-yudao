package com.liangcha.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.common.enums.ErrorCodeConstants;
import com.liangcha.common.exception.ServiceException;
import com.liangcha.common.redis.CacheService;
import com.liangcha.security.convert.service.OAuth2ClientConvert;
import com.liangcha.security.mq.OAuth2ClientProducer;
import com.liangcha.security.pojo.dao.OAuth2ClientMapper;
import com.liangcha.security.pojo.db.OAuth2ClientDO;
import com.liangcha.security.pojo.vo.OAuth2ClientCreateReqVO;
import com.liangcha.security.pojo.vo.OAuth2ClientUpdateReqVO;
import com.liangcha.security.service.OAuth2ClientService;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OAuth2.0 Client Service 实现类
 *
 * @author 凉茶
 */
@Component
@Validated
public class OAuth2ClientServiceImpl implements OAuth2ClientService {

    @Resource
    private OAuth2ClientMapper oauth2ClientMapper;

    @Resource
    private OAuth2ClientProducer oauth2ClientProducer;

    /**
     * 本地缓存
     * 使用redis缓存有问题(redis缓存的类还未加载就调用报错)
     */
    private static Map<String, OAuth2ClientDO> clientCache = new HashMap<>();

    /**
     * 给定字符串是否以任何一个字符串开始
     * 给定字符串和数组为空都返回 false
     *
     * @param str      给定字符串
     * @param prefixes 需要检测的开始字符串
     */
    public static boolean startWithAny(String str, Collection<String> prefixes) {
        if (StrUtil.isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return false;
        }

        for (CharSequence suffix : prefixes) {
            if (StrUtil.startWith(str, suffix, false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化缓存
     */
    @Override
    @PostConstruct
    public void initLocalCache() {
        // 第一步：查询数据
        List<OAuth2ClientDO> clients = oauth2ClientMapper.selectList(null);

        // 第二步：构建缓存
        clients.forEach((clientDO) -> {
            String clientId = clientDO.getClientId();
            clientCache.put(clientId, clientDO);
        });
    }

    @Override
    public Long createOAuth2Client(OAuth2ClientCreateReqVO createReqVO) {
        validateClientIdExists(null, createReqVO.getClientId());
        // 插入
        OAuth2ClientDO oauth2Client = OAuth2ClientConvert.INSTANCE.convert(createReqVO);
        oauth2ClientMapper.insert(oauth2Client);
        // 发送刷新消息
        oauth2ClientProducer.sendOAuth2ClientRefreshMessage();
        return oauth2Client.getId();
    }

    @Override
    public void updateOAuth2Client(OAuth2ClientUpdateReqVO updateReqVO) {
        // 校验存在
        validateOAuth2ClientExists(updateReqVO.getId());
        // 校验 Client 未被占用
        validateClientIdExists(updateReqVO.getId(), updateReqVO.getClientId());

        // 更新
        OAuth2ClientDO updateObj = OAuth2ClientConvert.INSTANCE.convert(updateReqVO);
        oauth2ClientMapper.updateById(updateObj);
        // 发送刷新消息
        oauth2ClientProducer.sendOAuth2ClientRefreshMessage();
    }

    @Override
    public void deleteOAuth2Client(Long id) {
        // 校验存在
        validateOAuth2ClientExists(id);
        // 删除
        oauth2ClientMapper.deleteById(id);
        // 发送刷新消息
        oauth2ClientProducer.sendOAuth2ClientRefreshMessage();
    }

    private void validateOAuth2ClientExists(Long id) {
        if (oauth2ClientMapper.selectById(id) == null) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_NOT_EXISTS.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_NOT_EXISTS.getMessage());
        }
    }

    private void validateClientIdExists(Long id, String clientId) {
        LambdaQueryWrapper<OAuth2ClientDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(OAuth2ClientDO::getClientId, clientId);
        OAuth2ClientDO client = oauth2ClientMapper.selectOne(lqw);
        if (client == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的客户端
        if (id == null) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_EXISTS.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_EXISTS.getMessage());
        }
        if (!client.getId().equals(id)) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_EXISTS.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_EXISTS.getMessage());
        }
    }

    @Override
    public OAuth2ClientDO getOAuth2Client(Long id) {
        return oauth2ClientMapper.selectById(id);
    }

    @Override
    public OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String authorizedGrantType, Collection<String> scopes, String redirectUri) {
        // 校验客户端存在、且开启
        OAuth2ClientDO client = CacheService.clientCache.get(clientId);
        if (client == null) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_NOT_EXISTS.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_NOT_EXISTS.getMessage());

        }
        if (ObjectUtil.notEqual(client.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_DISABLE.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_DISABLE.getMessage());

        }

        // 校验客户端密钥
        if (StrUtil.isNotEmpty(clientSecret) && ObjectUtil.notEqual(client.getSecret(), clientSecret)) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_CLIENT_SECRET_ERROR.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_CLIENT_SECRET_ERROR.getMessage());

        }
        // 校验授权方式
        if (StrUtil.isNotEmpty(authorizedGrantType) && !CollUtil.contains(client.getAuthorizedGrantTypes(), authorizedGrantType)) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS.getMessage());

        }
        // 校验授权范围
        if (CollUtil.isNotEmpty(scopes) && !CollUtil.containsAll(client.getScopes(), scopes)) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_SCOPE_OVER.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_SCOPE_OVER.getMessage());
        }
        // 校验回调地址
        if (StrUtil.isNotEmpty(redirectUri) && !startWithAny(redirectUri, client.getRedirectUris())) {
            throw new ServiceException(ErrorCodeConstants.OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH.getCode(), ErrorCodeConstants.OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH.getMessage());
        }
        return client;
    }
}