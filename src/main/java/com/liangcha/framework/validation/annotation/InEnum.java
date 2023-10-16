package com.liangcha.framework.validation.annotation;


import com.liangcha.framework.validation.handle.InEnumValidator;
import com.liangcha.framework.validation.interfaces.IntArr;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举值校验
 * Constraint 校验内容的实现
 *
 * @author 凉茶
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//定义规则
@Constraint(
        validatedBy = InEnumValidator.class
)
public @interface InEnum {

    /**
     * @return 实现 EnumValuable 接口的
     */
    Class<? extends IntArr> value();


    String message() default "必须在指定范围 {value}";

    /**
     * 下面两个都是实现自定义校验规则必须写的
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
