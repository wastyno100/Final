package com.example.back.mapper;

import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//xml파일의 내용을 가져오기 위해 @Mapper 어노테이션을 입력
@Mapper
public interface UserMapper {

     User getUser(String id);
//     String getUser(String id);
     void addUser(User user);

     boolean checkId(String id);

//     String getRole(String id);
}
