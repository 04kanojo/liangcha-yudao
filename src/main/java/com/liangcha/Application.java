package com.liangcha;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


/**
 * EnableCreateCacheAnnotation 开启jetcache注解
 * EnableMethodCache 开启方法注解
 * ConfigurationPropertiesScan 开启扫描yml配置文件
 * MapperScan 开启mapper扫描
 *
 * @author 凉茶
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan("com.liangcha.system.dao")
@EnableMethodCache(basePackages = "com.liangcha.framework")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
