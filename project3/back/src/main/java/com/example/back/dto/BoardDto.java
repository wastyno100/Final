package com.example.back.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BoardDto {
    private int boardNo;
    private String title;
    private String content;
    private String boardCate;
    private String boardDate;
    private String eventStart;
    private String eventEnd;
    private String boardImg;

    private int userNo;
}


