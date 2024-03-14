package com.example.back.service;

import com.example.back.dto.BoardDto;
import com.example.back.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> boardData() {
        return boardMapper.boardData();
    }

    public List<BoardDto> boardDetail(int boardNo) {
        return boardMapper.boardDetail(boardNo);
    }

    public void boardWrite(BoardDto boardDto) {
        boardMapper.boardWrite(boardDto);
    }

    public void boardUpdate(BoardDto boardDto){
        boardMapper.boardUpdate(boardDto);
    }

    public void boardDelete(int boardNo) {
        boardMapper.boardDelete(boardNo);
    }

}

