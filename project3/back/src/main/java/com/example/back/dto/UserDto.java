package com.example.back.dto;



import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userNo;
    private String id;
    private String pass;
    private String username;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String phone;
    private Boolean emailGet;
    private Timestamp joinDate;
    private String role;

    private String emailId;
    private String emailDomain;
    private String phone1;
    private String phone2;
    private String phone3;

    @Data
    public static class LoginRequest{
        private String id;
        private String pass;
    }
//    @Override
//    public HttpSession getCurrentSession() {
//        // RequestContextHolder를 통해 현재 요청에 대한 정보를 가져옵니다.
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//        // 현재 요청과 연결된 세션을 가져옵니다. 세션이 없다면 null을 반환합니다.
//        // true를 전달하면 현재 세션이 없을 경우 새로운 세션을 생성합니다.
//        // false를 전달하면 현재 세션이 없을 경우 null을 반환합니다.
//        HttpSession session = attr.getRequest().getSession(false); // 여기서는 새 세션을 생성하지 않도록 false를 사용합니다.
//        return session;
//    }
}
