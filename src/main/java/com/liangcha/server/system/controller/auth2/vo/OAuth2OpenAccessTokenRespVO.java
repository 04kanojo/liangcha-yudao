package com.liangcha.server.system.controller.auth2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("管理后台 - 【开放接口】访问令牌 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenAccessTokenRespVO {

    @ApiModelProperty("访问令牌")
    @JsonProperty("access_token")
    private String accessToken;

    @ApiModelProperty("刷新令牌")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @ApiModelProperty("令牌类型")
    @JsonProperty("token_type")
    private String tokenType;

    @ApiModelProperty("过期时间,单位：秒")
    @JsonProperty("expires_in")
    private LocalDateTime expiresTime;

    @ApiModelProperty("授权范围,如果多个授权范围，使用空格分隔")
    private List<String> scopes;

}
