package com.example.back.domain;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int userNo;
    private String id;
    private String pass;
    private String name;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String phone;
    private Boolean emailGet;
    private Timestamp joinDate;
    private String role;

    private String emailId;
    private String emailDomain;
    private String phone1;
    private String phone2;
    private String phone3;

}
