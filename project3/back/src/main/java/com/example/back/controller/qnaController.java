package com.example.back.controller;

import com.example.back.dto.AdminDto;
import com.example.back.service.BoardService;
import com.example.back.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class qnaController {
    @Autowired
    private QnaService qnaService;
    @GetMapping("/myQnaList")
    @CrossOrigin(origins = "*")
    public List<AdminDto.QnaDto> qna(int userNo) throws Exception {

        return qnaService.qnaData(userNo);
    }

    @PostMapping("/qnaCreate")
    @CrossOrigin(origins = "*")
    public void qna(@RequestBody AdminDto.QnaDto qnaDto) throws Exception {
        qnaService.qnaCreate(qnaDto);
    }
}
