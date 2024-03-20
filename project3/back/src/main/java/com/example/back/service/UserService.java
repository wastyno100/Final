package com.example.back.service;

import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;

import java.util.List;

public interface UserService {
    User getUser(String id);

    int login(String id, String pass);

    void addUser(User user);
}
