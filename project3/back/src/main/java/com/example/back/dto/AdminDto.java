package com.example.back.dto;

import lombok.Data;

@Data
public class AdminDto {
    private int userNo;
    private String name;
    private String id;
    private String pw;
    private String email;
    private String phone;
    private int roles;
    private String joinDate;

}


