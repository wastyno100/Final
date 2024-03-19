//package com.example.back.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF 보호를 활성화하고 토큰을 쿠키에 저장
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll() // 로그인 페이지는 모든 사용자에게 허용
//                .anyRequest().authenticated() // 나머지 요청은 인증된 사용자에게만 허용
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//}
