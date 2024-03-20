package com.example.back.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class MenuDto {
    private int menuNo;
    private int goodsNum;
    private String menuCate;
    private int menuPrice;
    private String menuTitle;
    private String menuContent;
    private String mImg;
    private String mdImg;
    private int heart;
    private Timestamp regDate;
    private int readCount;
}
