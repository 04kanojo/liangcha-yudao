package com.liangcha.mq.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liangcha.mq.message.AbstractRedisMessage;

/**
 * Redis Stream Message 抽象类
 *
 * @author 凉茶
 */
public abstract class AbstractStreamMessage extends AbstractRedisMessage {

    /**
     * 获得 Redis Stream Key
     *
     * @return Channel
     */
    @JsonIgnore // 避免序列化
    public abstract String getStreamKey();

}
