package com.example.back.service;

import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private UserDto.LoginRequest loginRequest;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public int login(String id, String pass) {
        User user = userMapper.getUser(id);
        int result = -1;

        if (user == null) {
            return result;
        }

        if (pass.equals(user.getPass())) {
            result = 1; // 비밀번호가 일치하면 로그인 성공
        } else {
            result = 0; // 비밀번호가 일치하지 않으면 로그인 실패
        }

        return result;
    }
}

