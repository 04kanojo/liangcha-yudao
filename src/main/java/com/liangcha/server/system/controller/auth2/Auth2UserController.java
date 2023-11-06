package com.liangcha.server.system.controller.auth2;

import cn.hutool.core.collection.CollUtil;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.framework.convert.auth2.OAuth2UserConvert;
import com.liangcha.server.system.controller.auth2.vo.OAuth2UserInfoRespVO;
import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.dept.domain.DeptDO;
import com.liangcha.system.dept.domain.PostDO;
import com.liangcha.system.dept.service.DeptService;
import com.liangcha.system.dept.service.PostService;
import com.liangcha.system.user.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.liangcha.common.pojo.CommonResult.success;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUserId;


/**
 * 提供给外部应用调用为主
 *
 * @author 凉茶
 */
@Api(tags = "管理后台 - OAuth2.0 用户")
@RestController
@RequestMapping("/system/oauth2/user")
@Valid
@Slf4j
public class Auth2UserController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;

    @Resource
    private PostService postService;

    @GetMapping("/get")
    @ApiOperation("获得用户基本信息")
    @PreAuthorize("@ss.hasScope('user.read')")
    public CommonResult<OAuth2UserInfoRespVO> getUserInfo() {
        // 获得用户基本信息
        AdminUserDO user = userService.getById(getLoginUserId());
        OAuth2UserInfoRespVO resp = OAuth2UserConvert.INSTANCE.convert(user);
        // 获得部门信息
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getById(user.getDeptId());
            resp.setDept(OAuth2UserConvert.INSTANCE.convert(dept));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(OAuth2UserConvert.INSTANCE.convertList(posts));
        }
        return success(resp);
    }

}
