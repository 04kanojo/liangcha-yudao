package com.liangcha.system.module.dept.service;


import com.liangcha.system.module.dept.dao.PostMapper;
import com.liangcha.system.module.dept.domain.PostDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位 Service 实现类
 *
 * @author 凉茶
 */
@Service
@Validated
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public List<PostDO> getPostList(Collection<Long> ids) {
        return postMapper.selectList(ids);
    }
}
