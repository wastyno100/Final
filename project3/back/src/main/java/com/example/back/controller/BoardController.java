package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/notice")
    public List<BoardDto> board() throws Exception {
        List<BoardDto> boardData = boardService.boardData();

        return boardData;
    }

    @GetMapping("/boardDetail")
    @CrossOrigin(origins = "*")
    public List<BoardDto> noticeDetail(int boardNo) throws Exception {
        List<BoardDto> boardDetail = boardService.boardDetail(boardNo);
        System.out.println("Dsadsadas"+boardDetail);

        return boardDetail;
    }

    @PostMapping("/boardWrite")
    @CrossOrigin(origins = "*")
    public void boardWrite(@RequestBody BoardDto boardDto) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@" + boardDto);
        boardService.boardWrite(boardDto);

    }

    @PutMapping("/boardUpdate")
    @CrossOrigin(origins = "*")
    public void boardUpdate(@RequestBody BoardDto boardDto) {
        System.out.println("gdgd" + boardDto);
        boardService.boardUpdate(boardDto);
    }

    @DeleteMapping("/boardDelete")
    @CrossOrigin(origins = "*")
    public void boardDelete(int boardNo) {
        boardService.boardDelete(boardNo);
    }
}
