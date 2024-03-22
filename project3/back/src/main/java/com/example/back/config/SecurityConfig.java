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
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        //admin권한은 manager의 권한을 상속 받으며, manager권한은 user의 권한을 상속받는다.
        hierarchy.setHierarchy("ROLE_admin>ROLE_manager\n" +
                "ROLE_manager > ROLE_user");

        return hierarchy;
    }

    //HTTP 보안설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //cors 비활성화
        //특정경로 접근제어
        http
                .csrf((auth) -> auth.disable())
                .authorizeHttpRequests((auth) -> auth
                        //경로 설정시 모든 사용자가 권한을 같는다.
                        .requestMatchers("/","/login",
                                "/member", "status", "/api/status"
                                ).permitAll()
                        //일반 페이지 경로는 권한 A가 있는 유저
                        .requestMatchers("/boardList")
                        .hasAnyRole("user")
                        //매니저 페이지는 경로 권한 B가 있는 유저
                        .requestMatchers("/manager/**").hasAnyRole("manager")
                        //어드민 페이지는 경로 권한 C가 있는 유저
                        .requestMatchers("/adminUser/**").hasAnyRole("admin")
                        .anyRequest().authenticated()
                );
        http
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지의 URL을 "/login"으로 설정합니다.
                        .loginProcessingUrl("/api/login") // 로그인 폼이 제출될 때 데이터를 처리할 URL을 "/login"으로 설정합니다.
                        .permitAll()
                );
        http
                .logout()
                .logoutUrl("/logout") // 로그아웃 URL 설정
                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 URL 설정
                .invalidateHttpSession(true) // HTTP 세션 무효화 설정
                .deleteCookies("JSESSIONID") // 쿠키 삭제 설정
                .permitAll();

        return http.build();
    }


}
