package com.example.back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    //역할 간의 계층 구조 정의
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        //C권한은 B의 권한을 상속 받으며, B권한은 A의 권한을 상속받는다.
        hierarchy.setHierarchy("ROLE_C>ROLE_B\n" +
                "ROLE_B > ROLE_A");

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
                        .requestMatchers("/login","/api/login","/member", "/api/member").permitAll()
                        //일반 페이지 경로는 권한 A가 있는 유저
                        .requestMatchers("/").hasAnyRole("A")
                        //매니저 페이지는 경로 권한 B가 있는 유저
                        .requestMatchers("/manager").hasAnyRole("B")
                        //어드민 페이지는 경로 권한 C가 있는 유저
                        .requestMatchers("localhost:5173/api/admin").hasAnyRole("C")
                        .anyRequest().authenticated()
                );
//        http
//                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지의 URL을 "/login"으로 설정합니다.
//                        .loginProcessingUrl("/api/login") // 로그인 폼이 제출될 때 데이터를 처리할 URL을 "/login"으로 설정합니다.
//                        .permitAll()
//                );

        return http.build();
    }


}
