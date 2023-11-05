package com.liangcha.system.auth2.service;

import cn.hutool.core.util.StrUtil;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.OAuth2Code;
import com.liangcha.system.auth2.pojo.domain.OAuth2AccessTokenDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;


/**
 * OAuth2 授予 Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class OAuth2GrantServiceImpl implements OAuth2GrantService {

    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private OAuth2CodeService oauth2CodeService;

    @Override
    public LoginUser grantAuthorizationCodeForAccessToken(String clientId, String code, String redirectUri, String state) {
        OAuth2Code codeDO = oauth2CodeService.consumeAuthorizationCode(code);

        // 校验 clientId 是否匹配
        if (!StrUtil.equals(clientId, codeDO.getClientId())) {
            throw exception(OAUTH2_GRANT_CLIENT_ID_MISMATCH);
        }

        // 校验 redirectUri 是否匹配
        if (!StrUtil.equals(redirectUri, codeDO.getRedirectUri())) {
            throw exception(OAUTH2_GRANT_REDIRECT_URI_MISMATCH);
        }

        // 校验 state 是否匹配 数据库 state 为 null 时，会设置为 "" 空串
        state = StrUtil.nullToDefault(state, "");
        if (!StrUtil.equals(state, codeDO.getState())) {
            throw exception(OAUTH2_GRANT_STATE_MISMATCH);
        }

        // 创建访问令牌
        return oauth2TokenService.createAccessToken(codeDO.getUserId(), codeDO.getClientId(), codeDO.getScopes());
    }

    @Override
    public OAuth2AccessTokenDO grantPassword(String username, String password, String clientId, List<String> scopes) {
//        // 使用账号 + 密码进行登录
//        AdminUserDO user = adminAuthService.authenticate(username, password);
//        Assert.notNull(user, "用户不能为空！"); // 防御性编程
//
//        // 创建访问令牌
//        return oauth2TokenService.createAccessToken(user.getId(), UserTypeEnum.ADMIN.getValue(), clientId, scopes);
        return null;
    }

    @Override
    public String grantAuthorizationCodeForCode(Long userId, Integer userType, String clientId, List<String> scopes, String redirectUri, String state) {
        return oauth2CodeService.createAuthorizationCode(userId, userType, clientId, scopes, redirectUri, state).getCode();
    }

    @Override
    public LoginUser grantRefreshToken(String refreshToken, String clientId) {
        return oauth2TokenService.refreshToken(refreshToken, clientId);
    }

}
