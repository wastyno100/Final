package com.project.seastory.backend.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestData {

    private static String email= "admin@gmail.com";
    private static String pass = "123456";

    @PostMapping("/testData")
    public Map<String, Object> testUser(@RequestBody UserData userdata, HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        if (email.equals(userdata.getEmail()) && pass.equals(userdata.getPass())){
            //로그인 성공
            Cookie cookie = new Cookie("member", String.valueOf(email));
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);

            result.put("status", "success");
            result.put("values", new String[]{email, pass});
        }else {
            return result;
        }
        return result;
    }



    public static class UserData {
        private String email;
        private String pass;

        public String getEmail(){
            return email;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public String getPass(){
            return pass;
        }

        public void setPass(String pass){
            this.pass = pass;
        }
    }

}

