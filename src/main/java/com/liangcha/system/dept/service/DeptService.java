package com.liangcha.system.dept.service;

import com.liangcha.system.dept.domain.DeptDO;

import java.util.List;

/**
 * 部门 Service 接口
 *
 * @author 凉茶
 */
public interface DeptService {
    /**
     * 获得指定部门的所有子部门
     *
     * @param id 部门编号
     * @return 子部门列表
     */
    List<DeptDO> getChildDeptList(Long id);
}
