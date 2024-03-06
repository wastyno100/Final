//package com.example.back.controller;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class TestDataController {
//    private static String email = "admin@gmail.com";
//    private static String pass = "123456";
//
//    @PostMapping("/login")
//    public Map<String, Object> testUser(@RequestBody UserData userData,
//                                        HttpServletResponse response) throws IOException {
//        Map<String, Object> result = new HashMap<>();
//        if(email.equals(userData.getEmail()) && pass.equals(userData.getPass())){
//            result.put("status", "success");
//            result.put("values", new String[]{email, pass});
//
//            Cookie cookie =new Cookie("member", String.valueOf(email));
//            cookie.setMaxAge(60*60);
//            response.addCookie(cookie);
//        }
//        else{
//            result.put("status", "false");
//        }
//        return result;
//    }
//
//    @PostMapping("/logout")
//    public Map<String, Object> logout(HttpServletResponse response){
//        Map<String, Object> result = new HashMap<>();
//
//        Cookie cookie =new Cookie("member", "");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        result.put("status", "success");
//
//        return result;
//    }
//    public static class UserData{
//        private String email;
//        private String pass;
//
//        public String getEmail(){
//            return email;
//        }
//        public void setEmail(String email){
//            this.email = email;
//        }
//
//        public String getPass(){
//            return pass;
//        }
//        public void setPass(String pass){
//            this.pass = pass;
//        }
//    }
//}
