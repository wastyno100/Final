package com.example.back.dto;



import lombok.*;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userNo;
    private String id;
    private String pass;
    private String name;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String phone;
    private String email_get;
    private Timestamp join_date;

    @Data
    public static class LoginRequest{
        private String id;
        private String pass;
    }
}
