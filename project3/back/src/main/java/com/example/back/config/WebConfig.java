package com.example.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true) // 쿠키나 인증 헤더와 같은 인증 관련 정보를 포함한 요청 허용
//                .maxAge(3600);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                // classpath: 방식으로 지정하면 약간의 지연이 생김 - 새로 고침 필요
                // .addResourceLocations("classpath:/resources/")

                // file: 프로토콜을 사용하면 업로드한 이미지가 바로 보인다.
                .addResourceLocations("file:./src/main/resources/static/boardImg/")
                .addResourceLocations("file:./src/main/resources/static/menu/")
                .addResourceLocations("file:./src/main/resources/static/reply/")
                .setCachePeriod(1);
    }
}

