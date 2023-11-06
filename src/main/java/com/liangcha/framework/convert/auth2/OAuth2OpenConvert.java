package com.liangcha.framework.convert.auth2;

import com.liangcha.common.utils.CollectionUtils;
import com.liangcha.server.system.controller.auth2.vo.OAuth2OpenAccessTokenRespVO;
import com.liangcha.server.system.controller.auth2.vo.OAuth2OpenAuthorizeInfoRespVO;
import com.liangcha.server.system.controller.auth2.vo.OAuth2OpenCheckTokenRespVO;
import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.OAuth2Approve;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface OAuth2OpenConvert {

    OAuth2OpenConvert INSTANCE = Mappers.getMapper(OAuth2OpenConvert.class);

    default OAuth2OpenAccessTokenRespVO convert(LoginUser bean) {
        OAuth2OpenAccessTokenRespVO respVO = convert0(bean);
        respVO.setTokenType("Bearer");
        return respVO;
    }

    OAuth2OpenAccessTokenRespVO convert0(LoginUser bean);

    default OAuth2OpenAuthorizeInfoRespVO convert(OAuth2ClientDO client, List<OAuth2Approve> approves) {
        // 构建 scopes
        List<Map<String, Boolean>> scopes = new ArrayList<>(client.getScopes().size());
        Map<String, OAuth2Approve> approveMap = CollectionUtils.convertMap(approves, OAuth2Approve::getScope);
        client.getScopes().forEach(scope -> {
            OAuth2Approve approve = approveMap.get(scope);
            Map<String, Boolean> map = new HashMap<>(1);
            map.put(scope, approve != null ? approve.getApproved() : false);
            scopes.add(map);
        });
        // 拼接返回
        return new OAuth2OpenAuthorizeInfoRespVO(new OAuth2OpenAuthorizeInfoRespVO.Client(client.getName(), client.getLogo()), scopes);
    }

    OAuth2OpenCheckTokenRespVO convert2(LoginUser user);
}
