package com.liangcha.system.dept.service;

import com.liangcha.system.dept.domain.DeptDO;

/**
 * 部门 Service 接口
 *
 * @author 凉茶
 */
public interface DeptService {
    /**
     * 获得指定部门的所有子部门
     *
     * @param deptId 部门编号
     * @return 子部门字符串
     */
    String getChildDeptByDeptId(Long deptId);

    DeptDO getById(Long id);
}
