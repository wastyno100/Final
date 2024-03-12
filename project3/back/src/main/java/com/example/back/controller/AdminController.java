package com.example.back.controller;

import com.example.back.dto.AdminDto;
import com.example.back.service.AdminService;
import com.example.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminUser")
    @CrossOrigin(origins = "*")
    public List<AdminDto> user() throws Exception {
        List<AdminDto> userData = adminService.userData();
        System.out.println(userData);
        return userData;
    }
}
