package com.liangcha.common.config;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义LocalDateTime序列化规则
 *
 * @author 凉茶
 */
@Component
public class LocalDateTimeSerializer implements ObjectSerializer {

    private static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type type, int i) {
        SerializeWriter out = jsonSerializer.out;
        if (object == null) {
            out.writeNull();
        } else {
            LocalDateTime result = (LocalDateTime) object;
            out.writeString(result.format(DateTimeFormatter.ofPattern(defaultPattern)));
        }
    }
}
