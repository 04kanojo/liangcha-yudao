package com.liangcha.system.dept.service;


import com.liangcha.system.dept.dao.PostMapper;
import com.liangcha.system.dept.domain.PostDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位 Service 实现类
 *
 * @author 芋道源码
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
