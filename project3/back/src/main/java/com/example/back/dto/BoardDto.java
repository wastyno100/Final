package com.example.back.dto;

import lombok.Data;

@Data
public class BoardDto {
    private int boardNo;
    private String title;
    private String content;
    private String boardDate;
    private String boardImg;
}


