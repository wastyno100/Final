package com.example.back.service;

import com.example.back.dto.MenuDto;
import com.example.back.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    public List<MenuDto> menuList(){
        return menuMapper.menuList();
    }

    public List<MenuDto> menuListBest(){
        return menuMapper.menuListBest();
    }
    public List<MenuDto> menuListMenuCate(){
        return menuMapper.menuListMenuCate();
    }

    public List<MenuDto> menuDetail(int menuNo) {
        return menuMapper.menuDetail(menuNo);
    }

    public void cart(int menuNo){
        menuMapper.cart(menuNo);
    }

    public List<MenuDto> menuBuy(int menuNo) {
        return menuMapper.menuBuy(menuNo);
    }

}

