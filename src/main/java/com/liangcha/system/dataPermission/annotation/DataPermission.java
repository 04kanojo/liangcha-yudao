package com.liangcha.system.dataPermission.annotation;

import java.lang.annotation.*;


/**
 * 数据权限
 *
 * @author 凉茶
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

    /**
     * 占位符关键字
     */
    String[] key() default "deptName";

    /**
     * 占位符替换值
     */
    String[] value() default "dept_id";

}
