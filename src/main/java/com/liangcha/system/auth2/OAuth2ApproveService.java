package com.liangcha.system.auth2;


import com.liangcha.framework.security.pojo.domain.OAuth2ApproveDO;

import java.util.List;

/**
 * OAuth2 批准 Service 接口
 * <p>
 * 从功能上，和 Spring Security OAuth 的 ApprovalStoreUserApprovalHandler 的功能，记录用户针对指定客户端的授权，减少手动确定。
 *
 * @author 芋道源码
 */
public interface OAuth2ApproveService {

    /**
     * 获得用户的批准列表，排除已过期的
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @return 是否授权通过
     */
    List<OAuth2ApproveDO> getApproveList(Long userId, Integer userType, String clientId);

}
