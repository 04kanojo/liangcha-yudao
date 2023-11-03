package com.liangcha.system.auth2.service;

import cn.hutool.core.collection.CollUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.system.auth2.pojo.OAuth2Approve;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static com.liangcha.common.utils.CollectionUtils.convertSet;

/**
 * OAuth2 批准 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OAuth2ApproveServiceImpl implements OAuth2ApproveService {

    @Resource
    private Cache<String, List<OAuth2Approve>> approveCache;

    @Override
    public List<OAuth2Approve> getApproveList(Long userId, String clientId) {
        return approveCache.get(userId + clientId);
    }

    @Override
    public boolean checkAutoApproval(Long userId, Integer userType, OAuth2ClientDO clientDO, Collection<String> requestedScopes) {
        String clientId = clientDO.getClientId();
        // 1.基于 Client 的自动授权计算，如果 scopes 都在自动授权中，则返回 true 通过
        if (CollUtil.containsAll(clientDO.getAutoApproveScopes(), requestedScopes)) {
            // 如果所有范围都已自动批准,存入缓存
            List<OAuth2Approve> approves = new ArrayList<>(requestedScopes.size());
            for (String scope : requestedScopes) {
                approves.add(saveApprove(userId, userType, clientId, scope, true));
            }
            // 存入缓存
            approveCache.put(userId + clientId, approves);
            return true;
        }

        // 2.基于 LoginUser 已经批准的授权。如果 scopes 都包含，则返回 true 通过
        List<OAuth2Approve> approves = getApproveList(userId, clientId);
        // 只保留同意的
        Set<String> scopes = convertSet(approves, OAuth2Approve::getScope, OAuth2Approve::getApproved);
        return CollUtil.containsAll(scopes, requestedScopes);
    }

    @Override
    public boolean updateAfterApproval(Long userId, Integer userType, String clientId, Map<String, Boolean> requestedScopes) {
        // 如果 requestedScopes 为空，说明没有要求，则返回 true 通过
        if (CollUtil.isEmpty(requestedScopes)) {
            return true;
        }

        // 更新批准的信息,需要至少有一个同意,如果有一个同意也返回给前端
        boolean success = false;
        List<OAuth2Approve> approves = new ArrayList<>(requestedScopes.size());
        /*
            使用entrySet的原因：减少查找开销
            当你需要遍历Map的键值对，但需要同时访问键和值时，使用entrySet()比先获取键集合和值集合再通过键去查找值更高效。因为使用entrySet()可以避免重复查找键和值
         */
        for (Map.Entry<String, Boolean> entry : requestedScopes.entrySet()) {
            if (entry.getValue()) {
                success = true;
            }
            approves.add(saveApprove(userId, userType, clientId, entry.getKey(), entry.getValue()));
        }
        approveCache.put(userId + clientId, approves);
        return success;
    }

    OAuth2Approve saveApprove(Long userId, Integer userType, String clientId, String scope, Boolean approved) {
        return new OAuth2Approve()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScope(scope)
                .setApproved(approved);
    }
}
