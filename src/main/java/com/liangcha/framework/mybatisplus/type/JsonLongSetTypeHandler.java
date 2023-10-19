package com.liangcha.framework.mybatisplus.type;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.liangcha.common.utils.JsonUtils;

import java.util.Set;

/**
 * 在我们将字符串反序列化为 Set 并且泛型为 Long 时，如果每个元素的数值太小，会被处理成 Integer 类型，导致类型转换失败
 * <p>
 * 例如说: RoleDO 的 dataScopeDeptIds 属性
 *
 * @author 凉茶
 */
public class JsonLongSetTypeHandler extends AbstractJsonTypeHandler<Object> {

    //初始化类型
    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<Set<Long>>() {
    };

    @Override
    protected Object parse(String json) {
        return JsonUtils.parseObject(json, TYPE_REFERENCE);
    }

    @Override
    protected String toJson(Object obj) {
        return JsonUtils.toJsonString(obj);
    }

}
