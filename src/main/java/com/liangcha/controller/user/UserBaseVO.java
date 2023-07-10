package com.liangcha.controller.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author 凉茶
 */
@Data
@ApiModel
public class UserBaseVO {

    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    @ApiModelProperty("用户昵称")
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickname;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("部门ID")

    private Long deptId;

    @ApiModelProperty("岗位编号数组")
    private Set<Long> postIds;

    @ApiModelProperty("用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("用户性别")
    private Integer sex;

    @ApiModelProperty("用户头像")
    private String avatar;

}
