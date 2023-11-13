package com.liangcha.system.module.file.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.common.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文件表
 * 每次文件上传，都会记录一条记录到该表中
 * 问：为什么要用uuid生成文件名,并且记录原文件名？
 * 答：因为预览使用的kkfileview，使用中文命名预览失败
 *
 * @author 凉茶
 */
@TableName("system_file")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileDO extends BaseDO {

    /**
     * 原文件名
     */
    private String name;

    /**
     * uuid文件名
     */
    private String uuidName;

    /**
     * 桶
     */
    private String bucket;

    /**
     * 路径
     */
    private String path;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 文件类型（后缀）
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

}
