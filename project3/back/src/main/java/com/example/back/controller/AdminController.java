package com.example.back.controller;

import com.example.back.dto.AdminDto;
import com.example.back.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
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

    @PutMapping("/updateRole")
    @CrossOrigin(origins = "*")
    public void updateRole(@RequestBody AdminDto adminDto) {
        String role = adminDto.getRole();

        if (role.equals("관리자")) { adminDto.setRole("admin"); }
        if (role.equals("사업자")) { adminDto.setRole("manager"); }
        if (role.equals("일반")) { adminDto.setRole("user"); }

        adminService.updateRole(adminDto);
    }

    @GetMapping("/qnaList")
    @CrossOrigin(origins = "*")
    public List<AdminDto.QnaDto> qnaList() {

        return adminService.qnaData();
    }

    @PutMapping("/qnaAnswer")
    @CrossOrigin(origins = "*")
    public void qnaList(@RequestBody AdminDto.QnaDto qnaDto) {
        System.out.println(qnaDto);
        adminService.qnaAnswer(qnaDto);
    }

    @GetMapping("/reportList")
    @CrossOrigin(origins = "*")
    public List<AdminDto.ReportDto> reportList() {
        return adminService.ReportData();
    }

    @PutMapping("/reportKeep")
    @CrossOrigin(origins = "*")
    public void reportKeep(int replyNo) {
        adminService.reportKeep(replyNo);
    }

    @DeleteMapping("/reportDelete")
    @CrossOrigin(origins = "*")
    public void reportDelete(int replyNo) {
        adminService.reportDelete(replyNo);
    }
}
