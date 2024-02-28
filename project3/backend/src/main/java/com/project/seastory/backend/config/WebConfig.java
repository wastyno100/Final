//package com.project.seastory.backend.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        //cors를 적용할 패턴 정리 /**는 와일드카드
//        registry.addMapping("/**")
//                //자원공유 허락
//                .allowedOrigins("http://localhost:5173")
//                //HTTP에서 사용할 메서드 지정
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                //클라이언트 측의 CORS 요청에 허용되는 헤더 지정
//                .allowedHeaders("Custom-Header")
//                //클라이언트 측의 CORS 응답을 노출하는 헤더지정
//                .exposedHeaders("Custom-Header")
//                //클라이언트 측에 대한 응답에 쿠키 또는 인증헤더를 포함할 수 있는지 여부
//                .allowCredentials(true)
//                //원하는 시간만큼 리퀘스트를 캐싱
//                .maxAge(3600);
//
//    }
//}
