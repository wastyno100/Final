package com.example.back.mapper;

import com.example.back.dto.AdminDto;
import com.example.back.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

//xml파일의 내용을 가져오기 위해 @Mapper 어노테이션을 입력
@Mapper
public interface AdminMapper {
     List<AdminDto> userData();

     void updateRole(AdminDto adminDto);

     List<AdminDto.QnaDto> qnaData();

     void qnaAnswer(AdminDto.QnaDto qnaDto);

     List<AdminDto.ReportDto> reportData();

     void reportKeep(int replyNo);

     void reportDelete(int replyNo);

     void userDelete(int userNo);

}
