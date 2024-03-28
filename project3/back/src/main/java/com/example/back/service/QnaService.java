package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {
    private final QnaMapper qnaMapper;

    @Autowired
    public QnaService(QnaMapper qnaMapper){
        this.qnaMapper = qnaMapper;
    }

    public List<AdminDto.QnaDto> qnaData(int userNo){
        return qnaMapper.qnaData(userNo);
    }

    public void qnaCreate(AdminDto.QnaDto qnaDto) { qnaMapper.qnaCreate(qnaDto); }
}

