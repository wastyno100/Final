package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    @CrossOrigin(origins = "*")
    public List<BoardDto> board(@RequestParam("boardCate") String boardCate) throws Exception {
        List<BoardDto> boardData = boardService.boardData(boardCate);

        return boardData;
    }

    @GetMapping("/boardDetail")
    @CrossOrigin(origins = "*")
    public List<BoardDto> boardDetail(int boardNo) throws Exception {
        List<BoardDto> boardDetail = boardService.boardDetail(boardNo);

        return boardDetail;
    }

    @PostMapping("/boardWrite")
    @CrossOrigin(origins = "*")
    public void boardWrite(@RequestBody BoardDto boardDto) throws Exception {
        boardService.boardWrite(boardDto);

    }

    @PutMapping("/boardUpdate")
    @CrossOrigin(origins = "*")
    public void boardUpdate(@RequestBody BoardDto boardDto) {
        boardService.boardUpdate(boardDto);
    }

    @DeleteMapping("/boardDelete")
    @CrossOrigin(origins = "*")
    public void boardDelete(int boardNo) {
        boardService.boardDelete(boardNo);
    }
}
