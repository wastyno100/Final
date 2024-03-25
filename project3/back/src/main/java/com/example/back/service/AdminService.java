package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.dto.BoardDto;
import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<AdminDto> userData();

    void updateRole(AdminDto adminDto);


    List<AdminDto.QnaDto> qnaData();

    void qnaAnswer(AdminDto.QnaDto qnaDto);

    List<AdminDto.ReportDto> ReportData();

    void reportKeep(int replyNo);

    void reportDelete(int replyNo);
}
