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
    private String username;
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

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BizUser{
        private int bNo;
        private int userNo;
        private String storeName;
        private String bizName;
        private String texType;
        private String bStt;
        private String utccYn;
        private String bizZipcode;
        private String bizAddress1;
        private String bizAddress2;

        public String getBStt(){
            return bStt;
        }
    }
    public String getRole() {
        return role;
    }
}
