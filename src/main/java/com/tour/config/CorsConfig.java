package com.tour.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域的域名，如果允许携带cookie，则需要写域名
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("*");

        corsConfiguration.setAllowCredentials(true); //是否允许携带cookie
        corsConfiguration.addAllowedMethod("*"); //允许所有请求方法进行跨域
        corsConfiguration.addAllowedHeader("*");  //允许携带任何请求头信息


        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        //初始化cors配置元对象
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);


        //返回corsfilter实力，参数：cors配置源对象
        return new CorsFilter(configurationSource);
    }
}
