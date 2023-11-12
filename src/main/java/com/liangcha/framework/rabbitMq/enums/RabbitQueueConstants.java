package com.liangcha.framework.rabbitMq.enums;

/**
 * queue队列的枚举类
 * 问：为什么使用接口，不适用枚举
 * 答：因为jetCaChe缓存注解key值需要的是字符串，用接口方便
 *
 * @author 凉茶
 */
public interface RabbitQueueConstants {

    String SMS = "sms";
}
