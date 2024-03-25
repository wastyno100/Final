package com.example.back.controller;


import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserDto.LoginRequest loginRequest,
                                     Model model,
                                     HttpSession session,
                                     HttpServletRequest request, // HttpServletRequest 추가
                                     HttpServletResponse response)
            throws IOException {
        int result = userService.login(loginRequest.getId(), loginRequest.getPass());
        Map<String, Object> responseData = new HashMap<>();

        if (result != 1) { // 로그인 실패 시
            responseData.put("result", result); // 실패 코드 반환
        } else { // 로그인 성공 시
            User user = userService.getUser(loginRequest.getId());
            session.invalidate(); // 기존 세션 무효화
            HttpSession newSession = request.getSession(true); // 새 세션 생성

            // 세션에 사용자 정보 저장
            newSession.setAttribute("isLogIn", true);
            newSession.setAttribute("userId", user.getId());
            newSession.setAttribute("userNo", user.getUserNo());
            newSession.setAttribute("role", user.getRole());
            newSession.setMaxInactiveInterval(30 * 60); // 세션 타임아웃 30분 설정

            responseData.put("result", result); // 성공 코드 반환
            responseData.put("userId", user.getId()); // 사용자 아이디 반환
            responseData.put("role", user.getRole()); // 사용자 역할 반환
            responseData.put("userNo", user.getUserNo()); // 사용자 고윺번호 반환
        }

        return responseData;
    }

//    @PostMapping("/login")
////    @CrossOrigin(origins = "http://localhost:5173")
//    public int login(@RequestBody UserDto.LoginRequest loginRequest,
//                     Model model,
//                     HttpSession session,
//                     HttpServletRequest request, // HttpServletRequest 추가
//                     HttpServletResponse response)
//            throws IOException {
//        System.out.println("@@@@@" + loginRequest.getId() + loginRequest.getPass());
//        int result = userService.login(loginRequest.getId(), loginRequest.getPass());
//
//        if (result != 1) { // 로그인 실패 시
//            // 적절한 실패 메시지 반환
//            return result;
//        } else { // 로그인 성공 시
//            // 기존 세션 무효화 및 새 세션 생성
//            String originalSessionId = session.getId();
//            session.invalidate();
//            HttpSession newSession = request.getSession(true);
//
//            // 로그인 성공 후 세션에 사용자 정보 저장
//            User user = userService.getUser(loginRequest.getId());
//            newSession.setAttribute("isLogIn", true);
//            newSession.setAttribute("userId", user.getId());
//            newSession.setAttribute("role", user.getRole()); //사용자 권한 저장
//            System.out.println(user.getRole());
//            newSession.setMaxInactiveInterval(30 * 60); // 세션 타임아웃 30분 설정
//
//
//            // 쿠키 설정
//            Cookie sessionCookie = new Cookie("JSESSIONID", newSession.getId());
//            sessionCookie.setPath("/");
//            sessionCookie.setHttpOnly(true);
//            sessionCookie.setMaxAge(60 * 60);
////            sessionCookie.setSecure(true); // HTTPS 환경에서만 사용
//            response.addCookie(sessionCookie);
//
//            model.addAttribute("isLogIn", true);
//            model.addAttribute("userId", user.getId());
//            model.addAttribute("role", user.getRole());
//            model.addAttribute("user", user);
//            System.out.print("로그인 성공" + user.getId());
//            System.out.println(user.getRole());
//
//            return result;
//        }
//    }

    @PostMapping("/logout")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null 반환
        if (session != null) {
            session.invalidate(); // 세션 무효화

            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "로그아웃성공";
        }
        return "로그아웃 실패: 세션이 존재하지 않습니다.";
    }

    @PostMapping("/member")
//    @CrossOrigin(origins = "http://localhost:5173")
    public String joinUser(@RequestBody User user) {
        System.out.println(user);
        user.setEmail(user.getEmailId() + "@" + user.getEmailDomain());
        user.setPhone(user.getPhone1() + "-" + user.getPhone2() + "-" + user.getPhone3());
        if (user.getEmailGet() != null) {
            user.setEmailGet(user.getEmailGet());
        }
        user.setRole("user");
        userService.addUser(user);
        return "회원가입이 성공적으로 완료되었습니다.";
    }

    public class LoginStatus {
        private final boolean isLogIn;
        private final String userId;

        public LoginStatus(boolean isLogIn, String userId) {
            this.isLogIn = isLogIn;
            this.userId = userId;
        }

        public boolean isLogIn() {
            return isLogIn;
        }

        public String getUserId() {
            return userId;
        }
    }

    @PostMapping("/status")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public LoginStatus checkLoginStatus(HttpSession session) {
        boolean isLogIn = session.getAttribute("isLogIn") != null && (Boolean) session.getAttribute("isLogIn");
        String userId = isLogIn ? (String) session.getAttribute("userId") : null;

        return new LoginStatus(isLogIn, userId);
    }

    @GetMapping("/checkId")
    public boolean checkId(@RequestParam(value = "id")String id) {

        return userService.checkId(id);
    }

    @GetMapping("/userData")
    public User userData(String id){
        return userService.getUser(id);
    }
}