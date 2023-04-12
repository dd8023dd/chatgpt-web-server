package com.home.chat.configrations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");   //可以通过的ip，*代表所有，可以使用指定的ip，多个的话可以用逗号分隔，默认为*
        corsConfiguration.addAllowedHeader("*");  //请求支持的头信息，默认为*，所有
        corsConfiguration.addAllowedMethod("*");  //请求方式 默认为*,所有
        corsConfiguration.setAllowCredentials(true);  //支持证书，默认为true
        return corsConfiguration;
    }

    // 通过添加 Filter 的方式，配置 CORS 规则，并手动指定对哪些接口有效
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());  // CORS 配置对所有接口都有效
        return new CorsFilter(source);
    }


}
