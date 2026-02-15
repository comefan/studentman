package com.fan.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/1 20:18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        WebMvcConfigurer.super.configurePathMatch(configurer);
        // 指定controller统一的接口前缀
        configurer.addPathPrefix("/api",clazz -> clazz.isAnnotationPresent(RestController.class));
    }
    @Resource
    private JwtInterceptor jwtInterceptor;
    //加自定义拦截器 JwtInterceptor,设置拦截规则

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/files/**")
                .excludePathPatterns("/api/bookType/upload")
                .excludePathPatterns("/api/role/**")
                .excludePathPatterns("/api/user/register");
    }
}
