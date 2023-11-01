package com.liangcha.server.controller.auth2;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.framework.convert.auth.OAuth2OpenConvert;
import com.liangcha.framework.security.utils.OAuth2Utils;
import com.liangcha.server.controller.auth2.vo.OAuth2OpenAccessTokenRespVO;
import com.liangcha.server.controller.auth2.vo.OAuth2OpenAuthorizeInfoRespVO;
import com.liangcha.system.auth2.enums.OAuth2GrantTypeEnum;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2AccessTokenDO;
import com.liangcha.system.auth2.pojo.domain.OAuth2ApproveDO;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import com.liangcha.system.auth2.service.OAuth2ApproveService;
import com.liangcha.system.auth2.service.OAuth2ClientService;
import com.liangcha.system.auth2.service.OAuth2GrantService;
import com.liangcha.system.user.enums.UserTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.liangcha.common.enums.ErrorCodeEnum.BAD_REQUEST;
import static com.liangcha.common.pojo.CommonResult.success;
import static com.liangcha.common.utils.CollectionUtils.convertList;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUserId;
import static com.liangcha.system.auth2.enums.OAuth2GrantTypeEnum.AUTHORIZATION_CODE;

/**
 * @author 凉茶
 */
@Api("管理后台 - OAuth2.0 授权")
@RestController
@RequestMapping("/system/oauth2")
@Validated
public class OAuth2OpenController {

    @Resource
    private OAuth2GrantService oauth2GrantService;

    @Resource
    private OAuth2ClientService oauth2ClientService;

    @Resource
    private OAuth2ApproveService oauth2ApproveService;


    private static OAuth2GrantTypeEnum getGrantTypeEnum(String responseType) {
        if (StrUtil.equals(responseType, "code")) {
            return AUTHORIZATION_CODE;
        }

        throw exception(BAD_REQUEST, "response_type 参数值只允许 code");
    }

    /**
     * 授权码 authorization_code 模式时：code + redirectUri + state 参数
     * 密码 password 模式时：username + password + scope 参数
     * 刷新 refresh_token 模式时：refreshToken 参数
     * 客户端 client_credentials 模式：scope 参数
     * <p>
     * 注意，默认需要传递 client_id + client_secret 参数
     */
    @PostMapping("/token")
    @PermitAll
    @ApiOperation("获得访问令牌")
    public CommonResult<OAuth2OpenAccessTokenRespVO> postAccessToken(HttpServletRequest request,
                                                                     @RequestParam("grant_type") String grantType,
                                                                     @RequestParam(value = "code", required = false) String code, // 授权码模式
                                                                     @RequestParam(value = "redirect_uri", required = false) String redirectUri, // 授权码模式
                                                                     @RequestParam(value = "state", required = false) String state, // 授权码模式
                                                                     @RequestParam(value = "username", required = false) String username, // 密码模式
                                                                     @RequestParam(value = "password", required = false) String password, // 密码模式
                                                                     @RequestParam(value = "scope", required = false) String scope, // 密码模式
                                                                     @RequestParam(value = "refresh_token", required = false) String refreshToken) { // 刷新模式
        //获取授权范围
        List<String> scopes = OAuth2Utils.buildScopes(scope);

        // 校验授权类型
        OAuth2GrantTypeEnum grantTypeEnum = OAuth2GrantTypeEnum.getByGranType(grantType);
        if (grantTypeEnum == null) {
            throw exception(BAD_REQUEST.getCode(), StrUtil.format("未知授权类型({})", grantType));
        }

        // 校验客户端
        String[] clientIdAndSecret = getBasicAuthorization(request);
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientIdAndSecret[0], clientIdAndSecret[1], grantType, scopes, redirectUri);

        // 根据授权模式，获取访问令牌
        LoginUser loginUser = null;
        switch (grantTypeEnum) {
            // 授权码模式
            case AUTHORIZATION_CODE:
                loginUser = oauth2GrantService.grantAuthorizationCodeForAccessToken(client.getClientId(), code, redirectUri, state);
                break;

            // 密码模式
            case PASSWORD:
                OAuth2AccessTokenDO oAuth2AccessTokenDO = oauth2GrantService.grantPassword(username, password, client.getClientId(), scopes);
                break;

            default:
                throw new IllegalArgumentException("未知授权类型：" + grantType);
        }
        return success(OAuth2OpenConvert.INSTANCE.convert(loginUser));
    }

    @GetMapping("/authorize")
    @ApiOperation("获得授权信息")
    public CommonResult<OAuth2OpenAuthorizeInfoRespVO> authorize(@RequestParam("clientId") String clientId) {
        // 0. 校验用户已经登录。通过 Spring Security 实现

        // 1. 获得 Client 客户端的信息
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientId);

        // 2. 获得用户已经授权的信息
        List<OAuth2ApproveDO> approves = oauth2ApproveService.getApproveList(getLoginUserId(), getUserType(), clientId);

        // 拼接返回
        return success(OAuth2OpenConvert.INSTANCE.convert(client, approves));
    }

    /**
     * 对应 Spring Security OAuth 的 AuthorizationEndpoint 类的 approveOrDeny 方法
     * <p>
     * 场景一：【自动授权 autoApprove = true】
     * 刚进入 sso.vue 界面，调用该接口，用户历史已经给该应用做过对应的授权，或者 OAuth2Client 支持该 scope 的自动授权
     * 场景二：【手动授权 autoApprove = false】
     * 在 sso.vue 界面，用户选择好 scope 授权范围，调用该接口，进行授权。此时，approved 为 true 或者 false
     * <p>
     * 因为前后端分离，Axios 无法很好的处理 302 重定向，所以和 Spring Security OAuth 略有不同，返回结果是重定向的 URL，剩余交给前端处理
     */
    @PostMapping("/authorize")
    @ApiOperation("申请授权")
    public CommonResult<String> approveOrDeny(@RequestParam("response_type") String responseType,
                                              @RequestParam("client_id") String clientId,
                                              @RequestParam(value = "scope", required = false) String scope,
                                              @RequestParam("redirect_uri") String redirectUri,
                                              @RequestParam(value = "auto_approve") Boolean autoApprove,
                                              @RequestParam(value = "state", required = false) String state) {
        @SuppressWarnings("unchecked")
        Map<String, Boolean> scopes = JSON.parseObject(scope, Map.class);
        // 1.1 校验 responseType 是否满足 code 或者 token 值
        OAuth2GrantTypeEnum grantTypeEnum = getGrantTypeEnum(responseType);
        // 1.2 校验 redirectUri 重定向域名是否合法 + 校验 scope 是否在 Client 授权范围内
        OAuth2ClientDO client = oauth2ClientService.validOAuthClientFromCache(clientId, null, grantTypeEnum.getGrantType(), scopes.keySet(), redirectUri);

        // 2.1 假设 approved 为 null，说明是场景一
        if (Boolean.TRUE.equals(autoApprove)) {
            // 如果无法自动授权通过，则不跳转
            if (!oauth2ApproveService.checkForPreApproval(getLoginUserId(), getUserType(), client, scopes.keySet())) {
                return success(null);
            }
        } else {
            // 2.2 假设 approved 非 null，说明是场景二
            // 如果计算后不通过，则失败
            if (!oauth2ApproveService.updateAfterApproval(getLoginUserId(), getUserType(), clientId, scopes)) {
                return success(OAuth2Utils.buildUnsuccessfulRedirect(redirectUri, responseType, state, "access_denied", "User denied access"));
            }
        }

        // 3.1 如果是 code 授权码模式，则发放 code 授权码，并重定向
        List<String> approveScopes = convertList(scopes.entrySet(), Map.Entry::getKey, Map.Entry::getValue);
        if (grantTypeEnum == AUTHORIZATION_CODE) {
            return success(getAuthorizationCodeRedirect(getLoginUserId(), client, approveScopes, redirectUri, state));
        }
        throw exception(BAD_REQUEST, "只允许授权码模式");
    }

    /**
     * 拼接重定向url
     */
    private String getAuthorizationCodeRedirect(Long userId, OAuth2ClientDO client, List<String> scopes, String redirectUri, String state) {
        // 1. 创建 code 授权码
        String authorizationCode = oauth2GrantService.grantAuthorizationCodeForCode(userId, getUserType(), client.getClientId(), scopes, redirectUri, state);
        // 2. 拼接重定向的 URL
        return OAuth2Utils.buildAuthorizationCodeRedirectUri(redirectUri, authorizationCode, state);
    }

    private Integer getUserType() {
        return UserTypeEnum.ADMIN.getCode();
    }

    /**
     * 获取请求中的客户端id和密钥
     *
     * @return 客户端id和密钥的字符串数组
     */
    private String[] getBasicAuthorization(HttpServletRequest request) {
        String[] clientIdAndSecret = OAuth2Utils.getBasicAuthorization(request);
        if (ArrayUtil.isEmpty(clientIdAndSecret) || clientIdAndSecret.length != 2) {
            throw exception(BAD_REQUEST.getCode(), "client_id 或 client_secret 未正确传递");
        }
        return clientIdAndSecret;
    }

}
