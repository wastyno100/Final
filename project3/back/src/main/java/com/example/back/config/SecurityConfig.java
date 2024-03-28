package com.example.back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    역할 간의 계층 구조 정의
//    @Bean
//    public RoleHierarchy roleHierarchy(){
//        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//        //admin권한은 manager의 권한을 상속 받으며, manager권한은 user의 권한을 상속받는다.
//        hierarchy.setHierarchy("ROLE_admin>ROLE_manager\n" +
//                "ROLE_manager > ROLE_user");
//
//        return hierarchy;
//    }

    //    HTTP 보안설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //cors 비활성화
        //특정경로 접근제어
        http
                .cors().disable()
                .csrf((auth) -> auth.disable())
                .authorizeHttpRequests((auth) -> auth
                                //경로 설정시 모든 사용자가 권한을 같는다.
//                        .requestMatchers("/","/login", "api/login",
//                                "/member", "status", "/api/status", "api/logout", "logout"
//                                ).permitAll()
//                        //일반 페이지 경로는 권한 A가 있는 유저
//                        .requestMatchers("/boardList")
//                        .hasAnyRole("user")
//                        //매니저 페이지는 경로 권한 B가 있는 유저
//                        .requestMatchers("/manager/**").hasAnyRole("manager")
//                        //어드민 페이지는 경로 권한 C가 있는 유저
//                        .requestMatchers("/adminUser/**").hasAnyRole("admin")
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()

                );
        http
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지의 URL을 "/login"으로 설정합니다.
                        .loginProcessingUrl("/api/login")
                        .permitAll()
                );
        http
                .logout()
                .logoutUrl("/api/logout") // 로그아웃 URL 설정
                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 URL 설정
                .invalidateHttpSession(true) // HTTP 세션 무효화 설정
                .deleteCookies("JSESSIONID") // 쿠키 삭제 설정
                .permitAll();

        return http.build();
    }

    // CORS 설정 추가
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // 수정된 부분
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.setAllowCredentials(true); // 쿠키/인증정보 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return new CorsFilter(source);
//    }
//
//    // HTTP 보안설정
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
////                .cors() // CORS 활성화 추가
////                .and()
//                .csrf((auth) -> auth.disable())
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/","/login", "api/login",
//                                "/member", "status", "/api/status", "api/logout", "logout"
//                        ).permitAll()
//                        .requestMatchers("/boardList")
//                        .hasAnyRole("user")
//                        .requestMatchers("/manager/**").hasAnyRole("manager")
//                        .requestMatchers("/adminUser/**").hasAnyRole("admin")
//                        .anyRequest().authenticated()
//                );
//        http
//                .formLogin((auth) -> auth.loginPage("/login")
//                        .loginProcessingUrl("/api/login")
//                        .permitAll());
//        http
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll();
//
//        return http.build();
//    }
}