package com.example.back.controller;

import com.example.back.dto.UserDto;
import com.example.back.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    @CrossOrigin(origins = "*")
    public String login() throws Exception {
        List<UserDto> userData = userService.userData();
        System.out.println(userData);
        return "User Data: " + userData;

    }
}
