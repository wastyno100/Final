package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/user")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/notice")
    @CrossOrigin(origins = "*")
    public List<BoardDto> board() throws Exception {
        List<BoardDto> boardData = boardService.boardData();
        System.out.println(boardData);
        return boardData;
    }

    @GetMapping("/noticeDetail")
    @CrossOrigin(origins = "*")
    public List<BoardDto> noticeDetail(@RequestParam Object boardNo) throws Exception {
        System.out.println("받았다 : " + boardNo);
        List<BoardDto> boardData = boardService.boardData();
        return boardData;
    }
}
