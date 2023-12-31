package com.liangcha.system.module.dept.service;

import cn.hutool.core.collection.CollUtil;
import com.alicp.jetcache.anno.Cached;
import com.liangcha.common.utils.CollectionUtils;
import com.liangcha.system.module.dept.dao.DeptMapper;
import com.liangcha.system.module.dept.domain.DeptDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.liangcha.common.utils.CollectionUtils.convertSet;
import static com.liangcha.framework.redis.RedisKeyConstants.DEPT_CHILDREN_ID_LIST;

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
    @Cached(name = DEPT_CHILDREN_ID_LIST, key = "#id", expire = 10, timeUnit = TimeUnit.MINUTES)
    public String getChildDeptByDeptId(Long id) {
        //LinkedList头尾插入更快,ArrayList查询更快
        List<DeptDO> children = new LinkedList<>();
        //创建初始父id
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

    @Override
    public DeptDO getById(Long id) {
        return deptMapper.selectById(id);
    }

}
