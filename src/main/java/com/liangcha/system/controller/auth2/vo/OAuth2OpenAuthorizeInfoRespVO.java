package com.liangcha.system.controller.auth2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


/**
 * @author 凉茶
 */
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
    private List<Map<String, Boolean>> scopes;

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
