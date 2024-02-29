package com.example.spreact.mapper;

import com.example.spreact.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestDto> selectBoardList() throws Exception;

}
