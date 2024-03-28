package com.example.back.mapper;

import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//xml파일의 내용을 가져오기 위해 @Mapper 어노테이션을 입력
@Mapper
public interface UserMapper {

     User getUser(String id);

     List<User> getUserData(String id);

     void addUser(User user);

     void addBizUser(User.BizUser bizUser);

     boolean checkId(String id);

     String passCheck(String id);

     void updateUser(User user);

     List<UserDto> eventData(int userNo);
     void eventUpdate(int userNo);

}
