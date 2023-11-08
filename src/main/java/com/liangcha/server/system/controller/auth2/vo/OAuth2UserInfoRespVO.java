package com.liangcha.server.system.controller.auth2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 凉茶
 */
@ApiModel("管理后台 - OAuth2 获得用户基本信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2UserInfoRespVO {

    @ApiModelProperty("用户编号")
    private Long id;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("用户性别，参见 SexEnum 枚举类")
    private Integer sex;

    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 所在部门
     */
    private Dept dept;

    /**
     * 所属岗位数组
     */
    private List<Post> posts;

    @ApiModel("部门")
    @Data
    public static class Dept {

        @ApiModelProperty("部门编号")
        private Long id;

        @ApiModelProperty("部门名称")
        private String name;

    }

    @ApiModel("岗位")
    @Data
    public static class Post {

        @ApiModelProperty("岗位编号")
        private Long id;

        @ApiModelProperty("岗位名称")
        private String name;

    }

}
