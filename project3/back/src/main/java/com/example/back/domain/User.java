package com.example.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
}
