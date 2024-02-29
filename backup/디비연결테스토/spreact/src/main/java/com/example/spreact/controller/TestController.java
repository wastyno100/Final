package com.example.spreact.controller;

import com.example.spreact.dto.TestDto;
import com.example.spreact.service.TestService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {

    //todo
    // 클라이언트에서 get요청을 하면 test1() 메서드를 실행 -> TestService을 호출하여 데이터를 가져오라 함
    // TestService는 인터페이스니까, 실세는 TestServiceImpl 인듯 -> TestMapper한테 데이터를 db에서 가져오라고 함
    // TestMapper는 mybatis를 사용해서 db에서 데이터를 가져옴 (역할이 DAO랑 합쳐져있어서 나눠줘야할듯)
    // TestDto 얘는 컨트롤러, 서비스 이런 얘들끼리 서로 데이터를 보내줘야하니 데이터를 담기위해 필요한놈인듯


    @Autowired
    private TestService testService;

    @GetMapping(value = "/test")
    @CrossOrigin(origins = "*")
    public void test1() throws Exception{

        List<TestDto> list = testService.selectBoardList();
        System.out.println("데이터, 여기에 출력되다. " + list);

    }

    // 이거는 다른거 테스트용
    @PostMapping(value="/testCookie")
    @CrossOrigin(origins = "*")
    public void testCookie(HttpServletResponse res, @RequestBody HashMap<String, String> data){
        // 데이터
        String mail = "test@gmail.com";
        String pass = "123456a";
        String admin = "1";

        // 클라이언트에서 받은 데이터
        String getEmail = data.get("email");
        String getPass = data.get("pass");

        if (getEmail.equals(mail) && getPass.equals(pass)){
            Cookie myCookie = new Cookie("cookie123", mail);

            myCookie.setMaxAge(60*60);
            myCookie.setPath("/");

            res.addCookie(myCookie);
        }
    }


}