package com.liangcha.framework.mybatisplus.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;

import java.util.Set;

/**
 * 在我们将字符串反序列化为 Set 并且泛型为 Long 时，如果每个元素的数值太小，会被处理成 Integer 类型，导致类型转换失败
 * <p>
 * 例如说: RoleDO 的 dataScopeDeptIds 属性
 *
 * @author 凉茶
 */
public class JsonLongSetTypeHandler extends AbstractJsonTypeHandler<Object> {

    @Override
    protected Object parse(String json) {
        TypeReference<Set<Long>> typeReference = new TypeReference<Set<Long>>() {
        };
        return JSON.parseObject(json, typeReference);
    }

    @Override
    protected String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

}
