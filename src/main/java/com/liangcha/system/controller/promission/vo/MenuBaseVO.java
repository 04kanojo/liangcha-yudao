package com.liangcha.system.controller.promission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 菜单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MenuBaseVO {

    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    @ApiModelProperty("权限标识,仅菜单类型为按钮时，才需要传递")
    @Size(max = 100)
    private String permission;

    @ApiModelProperty("类型，参见 MenuTypeEnum 枚举类")
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @ApiModelProperty("显示顺序不能为空")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty("父菜单 ID")
    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;

    @ApiModelProperty("路由地址,仅菜单类型为菜单或者目录时，才需要传")
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @ApiModelProperty("菜单图标,仅菜单类型为菜单或者目录时，才需要传")
    private String icon;

    @ApiModelProperty("组件路径,仅菜单类型为菜单时，才需要传")
    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;

    @ApiModelProperty("组件名")
    private String componentName;

    @ApiModelProperty("状态,见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("是否可见")
    private Boolean visible;

    @ApiModelProperty("是否缓存")
    private Boolean keepAlive;

    @ApiModelProperty("是否总是显示")
    private Boolean alwaysShow;

}
