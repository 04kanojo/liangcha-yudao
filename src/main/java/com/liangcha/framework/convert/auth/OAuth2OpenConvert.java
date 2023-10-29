package com.liangcha.framework.convert.auth;

import com.liangcha.system.auth2.pojo.LoginUser;
import com.liangcha.system.auth2.pojo.domain.OAuth2ApproveDO;
import com.liangcha.system.auth2.pojo.domain.OAuth2ClientDO;
import com.liangcha.server.controller.auth2.OAuth2OpenAccessTokenRespVO;
import com.liangcha.server.controller.auth2.vo.OAuth2OpenAuthorizeInfoRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;


@Mapper
public interface OAuth2OpenConvert {

    OAuth2OpenConvert INSTANCE = Mappers.getMapper(OAuth2OpenConvert.class);

    default OAuth2OpenAccessTokenRespVO convert(LoginUser bean) {
        OAuth2OpenAccessTokenRespVO respVO = convert0(bean);
        respVO.setTokenType("Bearer".toLowerCase());
        return respVO;
    }

    OAuth2OpenAccessTokenRespVO convert0(LoginUser bean);

    default OAuth2OpenAuthorizeInfoRespVO convert(OAuth2ClientDO client, List<OAuth2ApproveDO> approves) {
        // 构建 scopes
        List<String> scopes = new ArrayList<>(client.getScopes().size());
        approves.forEach(approve -> scopes.add(approve.getScope()));
        // 拼接返回
        return new OAuth2OpenAuthorizeInfoRespVO(new OAuth2OpenAuthorizeInfoRespVO.Client(client.getName(), client.getLogo()), scopes);
    }
}
