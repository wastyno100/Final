package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.dto.CartDto;
import com.example.back.dto.MenuDto;
import com.example.back.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class MenuController {
    private final MenuService menuService;

    @Value("${upload.path}/menu")
    private String uploadPath;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menuList")
    public List<MenuDto> getAllMenu(){
        log.info("메뉴리스트 들어갔어");
        return menuService.menuList();
    }

    @GetMapping("/menuListBest")
    public List<MenuDto> getBestMenu(){
        log.info("베스트 메뉴 들어갔어");
        return menuService.menuListBest();
    }

    @PostMapping("/menuDetail")
    @CrossOrigin(origins = "*")
    public List<MenuDto> menuDetail(int menuNo) throws Exception {System.out.println("디테일요청!");
        List<MenuDto> menuDetail = menuService.menuDetail(menuNo);
        System.out.println("메뉴 디테일 잘들어갔어 : "+menuDetail);

        return menuDetail;
    }
    @PostMapping("/menuWrite")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadFile(@ModelAttribute MenuDto menuDto,
                                             @RequestParam(value = "uploadImg", required = false) List<MultipartFile> uploadImgs) throws Exception {
        System.out.println(uploadImgs);
        System.out.println(menuDto);
        List <String> files = new ArrayList<>();
        Path uploadDir = Paths.get(uploadPath + "/mImg/");
//        Path uploadDir2 = Paths.get(uploadPath + "/mdImg/");
        for (MultipartFile mImg : uploadImgs) {
            // 이미지 uuid 설정
            String uuid = UUID.randomUUID().toString();
            String fileName = StringUtils.cleanPath(uuid + "_" + mImg.getOriginalFilename());

            // UUID 설정 된 파일 이름을 dto에 넣고 db에 저장하자
            files.add(fileName);

            // 이미지를 업로드할 경로 설정
            Path filePath = uploadDir.resolve(fileName);

            // 이미지를 저장
            Files.copy(mImg.getInputStream(), filePath);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String filesJson = objectMapper.writeValueAsString(files);

        menuDto.setMImg(filesJson);

        menuService.menuWrite(menuDto);

        return ResponseEntity.ok("저장완료");
    }
    @DeleteMapping("/menuDelete")
    @CrossOrigin(origins = "*")
    public void menuDelete(@RequestParam int menuNo) {
        menuService.menuDelete(menuNo);
        log.info("삭제완료");
    }

    @PostMapping("/cartSave")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> cartSave(@RequestBody CartDto cartDto){
        System.out.println(cartDto);
        menuService.cartSave(cartDto);
        return ResponseEntity.ok("카트에 저장 되버렸다!");
    }

    @GetMapping("/cart")
    public List<CartDto> cart(int userNo){
        log.info("카트 리스트 들어갔어");
        return menuService.cart(userNo);
    }

    @PostMapping("/menuBuy")
    @CrossOrigin(origins = "*")
    public List<MenuDto> menuBuy(int menuNo) throws Exception {System.out.println("결제요청!");
        List<MenuDto> menuBuy = menuService.menuBuy(menuNo);
        System.out.println("잘들어갔어"+menuBuy);

        return menuBuy;
    }

}
