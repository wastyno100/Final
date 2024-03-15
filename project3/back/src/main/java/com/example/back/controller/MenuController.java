package com.example.back.controller;

import com.example.back.dto.MenuDto;
import com.example.back.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menuList") // 로컬호스트로 요청
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuDto> getAllMenu(){
        return menuService.findAll();
    }

}
