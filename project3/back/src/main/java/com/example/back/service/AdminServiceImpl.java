package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.dto.UserDto;
import com.example.back.mapper.AdminMapper;
import com.example.back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<AdminDto> userData() {
        return adminMapper.userData();
    }
}

