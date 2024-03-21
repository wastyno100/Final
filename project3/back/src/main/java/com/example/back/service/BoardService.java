package com.example.back.service;

import com.example.back.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardData(String boardCate);

    List<BoardDto> boardDetail(int boardNo);

    void boardWrite(BoardDto boardDto);

    void boardUpdate(BoardDto boardDto);

    void boardDelete(int boardNo);


}
