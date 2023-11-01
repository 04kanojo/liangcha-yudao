package com.liangcha.system.auth2.service;

import cn.hutool.core.collection.CollUtil;
import com.liangcha.common.utils.DateUtils;
import com.liangcha.system.auth2.dao.OAuth2ApproveMapper;
import com.liangcha.system.auth2.pojo.domain.OAuth2ApproveDO;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.liangcha.common.utils.CollectionUtils.convertSet;

/**
 * OAuth2 批准 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OAuth2ApproveServiceImpl implements OAuth2ApproveService {

    /**
     * 批准的过期时间，默认 30 天
     */
    private static final Integer TIMEOUT = 30 * 24 * 60 * 60; // 单位：秒

    @Resource
    private OAuth2ApproveMapper oauth2ApproveMapper;

    @Override
    public List<OAuth2ApproveDO> getApproveList(Long userId, Integer userType, String clientId) {
        List<OAuth2ApproveDO> approveDOs = oauth2ApproveMapper.selectListByUserIdAndUserTypeAndClientId(userId, userType, clientId);
        approveDOs.removeIf(o -> DateUtils.isExpired(o.getExpiresTime()));
        return approveDOs;
    }

    @Override
    @Transactional
    public boolean checkForPreApproval(Long userId, Integer userType, OAuth2ClientDO clientDO, Collection<String> requestedScopes) {
        String clientId = clientDO.getClientId();
        // 基于 Client 的自动授权计算，如果 scopes 都在自动授权中，则返回 true 通过
        if (CollUtil.containsAll(clientDO.getAutoApproveScopes(), requestedScopes)) {
            // 如果所有范围都已自动批准，则仍需要将批准添加到批准存储中。
            LocalDateTime expireTime = LocalDateTime.now().plusSeconds(TIMEOUT);
            for (String scope : requestedScopes) {
                saveApprove(userId, userType, clientId, scope, true, expireTime);
            }
            return true;
        }

        // 算上用户已经批准的授权。如果 scopes 都包含，则返回 true
        List<OAuth2ApproveDO> approveDOs = getApproveList(userId, userType, clientId);
        Set<String> scopes = convertSet(approveDOs, OAuth2ApproveDO::getScope, OAuth2ApproveDO::getApproved); // 只保留未过期的 + 同意的
        return CollUtil.containsAll(scopes, requestedScopes);
    }

    @Override
    @Transactional
    public boolean updateAfterApproval(Long userId, Integer userType, String clientId, Map<String, Boolean> requestedScopes) {
        // 如果 requestedScopes 为空，说明没有要求，则返回 true 通过
        if (CollUtil.isEmpty(requestedScopes)) {
            return true;
        }

        // 更新批准的信息
        boolean success = false; // 需要至少有一个同意
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(TIMEOUT);
        /*
            使用entrySet的原因：减少查找开销
            当你需要遍历Map的键值对，但需要同时访问键和值时，使用entrySet()可能比先获取键集合和值集合再通过键去查找值更高效。因为使用entrySet()可以避免重复查找键和值
         */
        for (Map.Entry<String, Boolean> entry : requestedScopes.entrySet()) {
            if (entry.getValue()) {
                success = true;
            }
            saveApprove(userId, userType, clientId, entry.getKey(), entry.getValue(), expireTime);
        }
        return success;
    }

    void saveApprove(Long userId, Integer userType, String clientId, String scope, Boolean approved, LocalDateTime expireTime) {
        // 先更新
        OAuth2ApproveDO approveDO = new OAuth2ApproveDO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScope(scope)
                .setApproved(approved)
                .setExpiresTime(expireTime);
        if (oauth2ApproveMapper.update(approveDO) == 1) {
            return;
        }
        // 失败，则说明不存在，插入数据
        oauth2ApproveMapper.insert(approveDO);
    }
}
