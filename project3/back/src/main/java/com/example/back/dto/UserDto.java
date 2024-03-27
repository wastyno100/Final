package com.example.back.dto;



import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userNo;
    private String id;
    private String pass;
    private String username;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String phone;
    private Boolean emailGet;
    private Timestamp joinDate;
    private String role;
    private int event;

    private String emailId;
    private String emailDomain;
    private String phone1;
    private String phone2;
    private String phone3;


    @Data
    public static class LoginRequest{
        private String id;
        private String pass;
    }

}
