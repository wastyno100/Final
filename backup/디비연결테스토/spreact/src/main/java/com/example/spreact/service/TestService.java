package com.example.spreact.service;

import com.example.spreact.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {
    List<TestDto> selectBoardList() throws Exception;
}
