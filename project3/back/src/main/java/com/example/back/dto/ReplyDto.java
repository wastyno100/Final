package com.example.back.dto;

import lombok.Data;

@Data

public class ReplyDto {
        private int replyNo;
        private int menuNo;
        private int userNo;
        private String replyContent;
        private int report;
        private String rImg;
        private String regDate;
        private int heart;

        private String id;
}
