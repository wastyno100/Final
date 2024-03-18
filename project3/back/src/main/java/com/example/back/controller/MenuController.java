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
        log.info("ddd");
        return menuService.menuList();
    }

    @GetMapping("/boardDetail")
    @CrossOrigin(origins = "*")
    public List<MenuDto> noticeDetail(int menuNo) throws Exception {
        List<MenuDto> menuDetail = menuService.menuDetail(menuNo);
        System.out.println("Dsadsadas"+menuDetail);

        return menuDetail;
    }



}
