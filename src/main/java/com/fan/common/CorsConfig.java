package com.fan.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/1 22:42
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*"); //设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 设置访问源请求方法
        source.registerCorsConfiguration("/**",corsConfiguration); //对接口配置跨域设置
        return new CorsFilter(source);
    }
}
