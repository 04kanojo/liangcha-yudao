package com.liangcha.server.system.controller.auth2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("管理后台 - 【开放接口】校验令牌 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenCheckTokenRespVO {

    @ApiModelProperty("用户编号")
    @JsonProperty("user_id")
    private Long userId;
    @ApiModelProperty("用户类型，参见 UserTypeEnum 枚举")
    @JsonProperty("user_type")
    private Integer userType;

    @ApiModelProperty("客户端编号")
    @JsonProperty("client_id")
    private String clientId;
    @ApiModelProperty("授权范围")
    private List<String> scopes;

    @ApiModelProperty("访问令牌")
    @JsonProperty("access_token")
    private String accessToken;

    @ApiModelProperty("过期时间")
    private LocalDateTime expiresTime;

}
