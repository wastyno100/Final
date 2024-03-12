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
}

