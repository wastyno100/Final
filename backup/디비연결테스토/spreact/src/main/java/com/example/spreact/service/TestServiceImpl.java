package com.example.spreact.service;

import com.example.spreact.mapper.TestMapper;
import com.example.spreact.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestDto> selectBoardList() throws Exception {
        return testMapper.selectBoardList();
    }
}
