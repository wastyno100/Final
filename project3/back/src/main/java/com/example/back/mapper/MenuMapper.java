package com.example.back.mapper;

import com.example.back.dto.CartDto;
import com.example.back.dto.MenuDto;
import com.example.back.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuDto> menuListBest();

    void menuWrite(MenuDto menuDto);

    List<MenuDto> menuDetail(int menuNo);

//    List<CartDto> cartSave(CartDto menuNo);
    void cartSave(CartDto cartDto);

    List<CartDto> cart(int userNo);

    List<ReplyDto> reply(int replyNo);

    List<MenuDto> menuBuy(int menuNo);

    List<MenuDto> menuList();

    void menuDelete(int menuNo);
}
