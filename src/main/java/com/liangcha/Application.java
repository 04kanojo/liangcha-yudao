package com.liangcha;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;


/**
 * EnableCaching 开启jetcache
 * EnableMethodCache 开启方法缓存(jetcache)
 * ConfigurationPropertiesScan 开启扫描yml配置文件
 * MapperScan 开启mapper扫描
 *
 * @author 凉茶
 */
@EnableCaching
@EnableMethodCache(basePackages = "com")
@SpringBootApplication
@MapperScan("com.liangcha.dao")
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
