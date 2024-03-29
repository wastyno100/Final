package com.example.back.mapper;

import com.example.back.dto.AdminDto;
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

    List<ReplyDto> getReply(int menuNo);

    void insertReply(ReplyDto replyDto);

    void replyReport(int replyNo);

    void replyLike(int replyNo);

    void menuLike(int menuNo);

    void plusCart(CartDto cartDto);
    void minusCart(CartDto cartDto);
    void deleteCart(int cartId);

    void cartDeleteAll(int userNo);

    List<AdminDto> buyUser(int userNo);
}
