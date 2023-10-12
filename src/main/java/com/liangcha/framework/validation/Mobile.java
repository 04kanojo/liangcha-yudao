package com.liangcha.framework.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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
        validatedBy = MobileValidator.class
)
public @interface Mobile {

    String message() default "手机号格式不正确";

    /**
     * 下面两个都是实现自定义校验规则必须写的
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
