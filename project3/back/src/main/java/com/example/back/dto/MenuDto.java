package com.example.back.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
