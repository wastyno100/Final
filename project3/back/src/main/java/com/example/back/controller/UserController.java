package com.example.back.controller;


import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.service.UserService;
import jakarta.annotation.security.RolesAllowed;
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
                        HttpServletResponse response)
                         throws IOException {
        System.out.println("@@@@@" + loginRequest.getId() + loginRequest.getPass());
        int result = userService.login(loginRequest.getId(), loginRequest.getPass());

//        if(result == -1){
//            return result;
//        }
//        else if(result == 0){
//            return result;
//        }
            User user = userService.getUser(loginRequest.getId());

            session.setAttribute("isLogin", true);

            model.addAttribute("user", user);

            System.out.print("로그인 성공");

            return result;

    }
    @PostMapping("/joinUser")
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
 }


