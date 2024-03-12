package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;

import java.util.List;

public interface AdminService {
    List<AdminDto> userData();
}
