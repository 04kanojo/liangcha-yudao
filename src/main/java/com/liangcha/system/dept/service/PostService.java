package com.liangcha.system.dept.service;


import com.liangcha.system.dept.domain.PostDO;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * 岗位 Service 接口
 *
 * @author 凉茶
 */
public interface PostService {

    /**
     * 获得符合条件的岗位列表
     *
     * @param ids 岗位编号数组。如果为空，不进行筛选
     * @return 部门列表
     */
    List<PostDO> getPostList(@Nullable Collection<Long> ids);
}
