package com.liangcha.system.auth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@ApiModel(description = "管理后台 - 授权页的信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenAuthorizeInfoRespVO {

    /**
     * 客户端
     */
    private Client client;

    @ApiModelProperty("scope 的选中信息,使用 List 保证有序性，Key 是 scope，Value 为是否选中")
    private List<String> scopes;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Client {

        @ApiModelProperty("应用名")
        private String name;

        @ApiModelProperty("应用图标")
        private String logo;

    }

}
