package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.dto.UserDto;
import com.example.back.mapper.AdminMapper;
import com.example.back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<AdminDto> userData() {
        return adminMapper.userData();
    }

    public void updateRole(AdminDto adminDto) { adminMapper.updateRole(adminDto); }

    public List<AdminDto.QnaDto> qnaData() {
        return adminMapper.qnaData();
    }

    public void qnaAnswer(AdminDto.QnaDto qnaDto) { adminMapper.qnaAnswer(qnaDto); }
}

