package com.example.back.service;

import com.example.back.domain.User;
import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private UserDto.LoginRequest loginRequest;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> getUserData(String id) {
        return userMapper.getUserData(id);
    }

    @Override
    public int login(String id, String pass) {
        User user = userMapper.getUser(id);
        int result = -1;

        if (user == null) {
            return result;
        }
        //입력한 비밀번호와 데이터베이스의 인코딩된 비밀번호를 매치
        if (passwordEncoder.matches(pass, user.getPass())) {
            result = 1; // 비밀번호가 일치하면 로그인 성공
        } else {
            result = 0; // 비밀번호가 일치하지 않으면 로그인 실패
        }

        return result;
    }

    @Override
    public void addUser(User user) {
        //비밀번호를 저장할 때 BCryptPasswordEncoder 를 통해 비밀번호를 암호화
        user.setPass(passwordEncoder.encode(user.getPass()));
        System.out.println(user.getPass());
        userMapper.addUser(user);
    }

    @Override
    public void addBizUser(User.BizUser bizUser) {
        userMapper.addBizUser(bizUser);
    }

    public boolean checkId(String id){
        User user = userMapper.getUser(id);

        return user != null;
    }

    @Override
    public boolean passCheck(String id, String pass) {
        String dbPass = userMapper.passCheck(id);
        boolean result = false;

        if(dbPass != null) {
            if (passwordEncoder.matches(pass, dbPass)) {
                result = true;

            }
        }
        return result;
    }

    @Override
    public void updateUser(User user) {

        user.setPass(passwordEncoder.encode(user.getPass()));

        userMapper.updateUser(user);
    }

    @Override
    public void eventUpdate(int userNo){
        userMapper.eventUpdate(userNo);
    }


    @Override
    public List<UserDto> eventData(int userNo){
        return userMapper.eventData(userNo);


    }
//    @Override
//    public String getRole(String id) {
//        return userMapper.getRole(id);
//    }
}

