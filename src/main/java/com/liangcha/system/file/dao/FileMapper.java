package com.liangcha.system.file.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.file.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作 Mapper
 *
 * @author 凉茶
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {

    default FileDO selectByName(String fileName) {
        return selectOne(new LambdaQueryWrapper<FileDO>().eq(FileDO::getName, fileName));
    }

}
