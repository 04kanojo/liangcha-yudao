package com.liangcha.system.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.file.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {

}
