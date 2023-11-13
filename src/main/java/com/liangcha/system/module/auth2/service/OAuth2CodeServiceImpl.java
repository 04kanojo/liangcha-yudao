package com.liangcha.system.module.auth2.service;

import cn.hutool.core.util.IdUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.system.module.auth2.pojo.OAuth2Code;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static com.liangcha.common.enums.ErrorCodeEnum.OAUTH2_CODE_NOT_EXISTS_OR_EXPIRE;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

/**
 * OAuth2.0 授权码 Service 实现类
 *
 * @author 凉茶
 */
@Service
@Validated
public class OAuth2CodeServiceImpl implements OAuth2CodeService {

    @Resource
    private Cache<String, OAuth2Code> codeCache;

    private static String generateCode() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public OAuth2Code createAuthorizationCode(Long userId, Integer userType, String clientId, List<String> scopes, String redirectUri, String state) {
        OAuth2Code codeDO = new OAuth2Code()
                .setCode(generateCode())
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setRedirectUri(redirectUri)
                .setState(state);
        codeCache.put(codeDO.getCode(), codeDO);
        return codeDO;
    }

    @Override
    public OAuth2Code consumeAuthorizationCode(String code) {
        OAuth2Code codeDO = codeCache.get(code);
        if (codeDO == null) {
            throw exception(OAUTH2_CODE_NOT_EXISTS_OR_EXPIRE);
        }
        codeCache.remove(code);
        return codeDO;
    }

}
