package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.dto.CartDto;
import com.example.back.dto.MenuDto;
import com.example.back.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/menuDetail")
    @CrossOrigin(origins = "*")
    public List<MenuDto> menuDetail(int menuNo) throws Exception {System.out.println("디테일요청!");
        List<MenuDto> menuDetail = menuService.menuDetail(menuNo);
        System.out.println("잘들어갔어"+menuDetail);

        return menuDetail;
    }
    @PostMapping("/cart")
    @CrossOrigin(origins = "*")
    public String savePageData(@RequestParam(value = "text") CartDto cartDto) {
        // 여기서 데이터를 저장하는 로직을 구현합니다. 예를 들어, 데이터베이스에 저장.
        // 이 예제에서는 단순히 받은 데이터를 콘솔에 출력합니다.
        menuService.cart(cartDto.getMenuNo());
        return "카트db에 저장";
    }
//    public List<CartDto> cart(int menuNo) throws Exception {System.out.println("카트요청!");
//        List<CartDto> cart = menuService.cart(menuNo);
//        System.out.println("잘들어갔어"+ cart);
//
//        return cart;
//    }

}
