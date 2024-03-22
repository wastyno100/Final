package com.example.back.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
        private int cartId;
        private int menuNo;
        private int userNo;
        private int menuCount;
    }
