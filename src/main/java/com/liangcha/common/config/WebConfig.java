package com.liangcha.common.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Web配置类
 *
 * @author 凉茶
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LocalDateTimeSerializer localDateTimeSerializer;

    /**
     * 配置跨域
     */
    @Bean
    public CorsFilter corsFilter() {
        //创建CorsConfiguration对象后添加配置
        CorsConfiguration config = new CorsConfiguration();
        //设置放行哪些原始域，这里直接设置为所有
        config.addAllowedOriginPattern("*");
        //你可以单独设置放行哪些原始域 config.addAllowedOrigin("http://localhost:2222");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //放行哪些请求方式，*代表所有
        config.addAllowedMethod("*");
        //是否允许发送Cookie，必须要开启，因为我们的JSESSIONID需要在Cookie中携带
        config.setAllowCredentials(true);
        //映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

    /**
     * 映射路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义json处理LocalDateTime规则
     * <p>
     * 注:这里使用的fastjson,并不是fastjson2
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //创建序列化配置
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        //注入自定义的序列化解析器
        serializeConfig.put(LocalDateTime.class, localDateTimeSerializer);
        config.setSerializeConfig(serializeConfig);
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect
        );

        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        converters.add(0, fastJsonHttpMessageConverter);
    }
}
