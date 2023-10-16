package com.liangcha.server.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 凉茶
 */
@ApiModel("管理后台 - 登录响应VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRespVO {

    @ApiModelProperty("用户编号")
    private Long userId;

    @ApiModelProperty("访问令牌")
    private String accessToken;

    @ApiModelProperty("刷新令牌")
    private String refreshToken;

    @ApiModelProperty("过期时间")
    private LocalDateTime expiresTime;

}
