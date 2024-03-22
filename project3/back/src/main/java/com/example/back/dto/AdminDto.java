package com.example.back.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AdminDto {
    private int userNo;
    private String username;
    private String id;
    private String pw;
    private String email;
    private String phone;
    private String role;
    private String joinDate;

    @Data
    public static class QnaDto {
        private int qnaNo;
        private int userNo;
        private String id;
        private String role;
        private String qnaTitle;
        private String qnaContent;
        private String qnaDate;
        private String answer;
        private String answerDate;
    }


}


