package com.example.back.service;

import com.example.back.dto.AdminDto;
import com.example.back.dto.CartDto;
import com.example.back.dto.MenuDto;
import com.example.back.dto.ReplyDto;
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

    public void menuWrite(MenuDto menuDto) {

        menuMapper.menuWrite(menuDto);
    }

    public List<MenuDto> menuDetail(int menuNo) {
        return menuMapper.menuDetail(menuNo);
    }
    public List<ReplyDto> reply(int userNo) {
        return menuMapper.reply(userNo);
    }
    public void cartSave(CartDto cartDto){
        menuMapper.cartSave(cartDto);
    }
    public List<CartDto> cart(int userNo) {
        return menuMapper.cart(userNo);
    }
    public List<MenuDto> menuBuy(int menuNo) {
        return menuMapper.menuBuy(menuNo);
    }
    public void menuDelete(int menuNo) {
        menuMapper.menuDelete(menuNo);
    }

    public List<ReplyDto> getReply(int menuNo) { return menuMapper.getReply(menuNo); }

    public void insertReply(ReplyDto replyDto) {
        menuMapper.insertReply(replyDto);
    }

    public void replyReport(int replyNo) {
        menuMapper.replyReport(replyNo);
    }

    public void replyLike(int replyNo) {
        menuMapper.replyLike(replyNo);
    }

    public void menuLike(int menuNo) {
        menuMapper.menuLike(menuNo);
    }

    public void plusCart(CartDto cartDto) {
        menuMapper.plusCart(cartDto);
    }
    public void minusCart(CartDto cartDto) {
        menuMapper.minusCart(cartDto);
    }
    public void deleteCart(int cartId) {
        menuMapper.deleteCart(cartId);
    }

    public List<AdminDto> buyUser(int userNo) {
        return menuMapper.buyUser(userNo);
    }

    public void cartDeleteAll(int userNo) {
        menuMapper.cartDeleteAll(userNo);
    }
}

