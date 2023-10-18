package com.liangcha.system.dept.service;

import cn.hutool.core.collection.CollUtil;
import com.liangcha.common.utils.CollectionUtils;
import com.liangcha.system.dept.dao.DeptMapper;
import com.liangcha.system.dept.domain.DeptDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.liangcha.common.utils.CollectionUtils.convertSet;

/**
 * 部门 Service 实现类
 *
 * @author 凉茶
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public String getChildDeptList(Long id) {
        //LinkedList头尾插入更快,ArrayList查询更快
        List<DeptDO> children = new LinkedList<>();
        Set<Long> parentIds = Collections.singleton(id);
        // 使用 Short.MAX_VALUE 避免 bug 场景下，存在死循环
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 查询当前层，所有的子部门
            List<DeptDO> depts = deptMapper.selectListByParentId(parentIds);
            // 1. 如果没有子部门，则结束遍历
            if (CollUtil.isEmpty(depts)) {
                break;
            }
            // 2. 如果有子部门，继续遍历
            children.addAll(depts);
            parentIds = convertSet(depts, DeptDO::getId);
        }
        return CollectionUtils.join(children, deptDO -> String.valueOf(deptDO.getId()), ",");
    }
}
