package com.example.back.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QouteDto {
    private int qouteNo;
    private String name;
    private int price;
    private String day;
}


