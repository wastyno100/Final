package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.dto.MenuDto;
import com.example.back.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class MenuController {
    private final MenuService menuService;


    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/menuList")
    public List<MenuDto> getAllMenu(){
        log.info("메뉴리스트 들어갔어");
        return menuService.menuList();
    }

    @GetMapping("/menuDetail")
    @CrossOrigin(origins = "*")
    public List<MenuDto> menuDetail(int menuNo) throws Exception {System.out.println("요청!");
        List<MenuDto> menuDetail = menuService.menuDetail(menuNo);
        System.out.println("잘들어갔어"+menuDetail);

        return menuDetail;
    }



}
