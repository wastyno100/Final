package com.example.back.mapper;

import com.example.back.dto.QouteDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

//xml파일의 내용을 가져오기 위해 @Mapper 어노테이션을 입력

public interface QouteMapper {
     List<QouteDto> qouteData(int qouteNo);
     void qouteDelete(int qouteNo);

}
