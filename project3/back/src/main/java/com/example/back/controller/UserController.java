package com.example.back.controller;


import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public int login(@RequestBody UserDto.LoginRequest loginRequest,
                     Model model,
                     HttpSession session,
                     HttpServletRequest request, // HttpServletRequest 추가
                     HttpServletResponse response)
            throws IOException {
        System.out.println("@@@@@" + loginRequest.getId() + loginRequest.getPass());
        int result = userService.login(loginRequest.getId(), loginRequest.getPass());

        if(result != 1){ // 로그인 실패 시
            // 적절한 실패 메시지 반환
            return result;
        } else { // 로그인 성공 시
            // 기존 세션 무효화 및 새 세션 생성
            String originalSessionId = session.getId();
            session.invalidate();
            HttpSession newSession = request.getSession(true);

            // 로그인 성공 후 세션에 사용자 정보 저장
            User user = userService.getUser(loginRequest.getId());
            newSession.setAttribute("isLogin", true);
            newSession.setMaxInactiveInterval(30*60); // 세션 타임아웃 30분 설정

            // 쿠키 설정
            Cookie sessionCookie = new Cookie("JSESSIONID", newSession.getId());
            sessionCookie.setHttpOnly(true);
//            sessionCookie.setSecure(true); // HTTPS 환경에서만 사용
            response.addCookie(sessionCookie);

            model.addAttribute("user", user);
            System.out.print("로그인 성공" + user.getId());

            return result;
        }
    }

    @PostMapping("/member")
    @CrossOrigin(origins = "*")
    public String joinUser(@RequestBody User user){
        System.out.println(user);
        user.setEmail(user.getEmailId() + "@" + user.getEmailDomain());
        user.setPhone(user.getPhone1() + "-" + user.getPhone2() + "-" + user.getPhone3());
        if (user.getEmailGet() != null) {
            user.setEmailGet(user.getEmailGet());
        }
        userService.addUser(user);
        return "회원가입이 성공적으로 완료되었습니다.";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("어드민페이지입니다.");
        return "admin";
    }
 }


