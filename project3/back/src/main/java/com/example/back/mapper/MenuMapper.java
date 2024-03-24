package com.example.back.mapper;

import com.example.back.dto.CartDto;
import com.example.back.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuDto> menuListBest();

    List<MenuDto> menuListMenuCate();

    List<MenuDto> menuDetail(int menuNo);

    List<CartDto> cart(int menuNo);

    List<MenuDto> menuBuy(int menuNo);

    List<MenuDto> menuList();
}
