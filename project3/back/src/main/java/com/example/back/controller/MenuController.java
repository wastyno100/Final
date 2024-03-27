package com.example.back.controller;

import com.example.back.dto.*;
import com.example.back.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Value("${upload.path}")
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

    @GetMapping("/reply")
    public List<ReplyDto> getReply(int userNo){
        log.info("리플 들어 갔어");
        return menuService.reply(userNo);
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

    @GetMapping("/getReply")
    @CrossOrigin(origins = "*")
    public List<ReplyDto> menuReply(int menuNo) {
        System.out.println("제발" + menuNo);
        List<ReplyDto> getReply = menuService.getReply(menuNo);

        return getReply;
    }

    @PostMapping("/insertReply")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> insertReply(@ModelAttribute ReplyDto replyDto,
                            @RequestParam(value = "uploadImg", required = false) List<MultipartFile> uploadImgs) throws IOException {

        List <String> files = new ArrayList<>();
        Path uploadDir = Paths.get(uploadPath + "/reply/");

        if(uploadImgs != null) {
            for (MultipartFile boardImg : uploadImgs) {
                // 이미지 uuid 설정
                String uuid = UUID.randomUUID().toString();
                String fileName = StringUtils.cleanPath(uuid + "_" + boardImg.getOriginalFilename());

                // UUID 설정 된 파일 이름을 dto에 넣고 db에 저장하자
                files.add(fileName);

                // 이미지를 업로드할 경로 설정
                Path filePath = uploadDir.resolve(fileName);

                // 이미지를 저장
                Files.copy(boardImg.getInputStream(), filePath);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String filesJson = objectMapper.writeValueAsString(files);

            replyDto.setRImg(filesJson);
            System.out.println("제발123123" + replyDto);
            menuService.insertReply(replyDto);
            return ResponseEntity.ok("저장완료");

        }

        menuService.insertReply(replyDto);
        return ResponseEntity.ok("저장완료");
    }

    @GetMapping("/getRImage/{replyImg}")
    public String replyImage(@PathVariable String replyImg) throws Exception {
        String imagePath = "http://localhost:8080/reply/" + replyImg;
        System.out.println(imagePath);
        return imagePath;
    }

    @GetMapping("/getMImage/{menuImg}")
    public String getImage(@PathVariable String menuImg) throws Exception {
        String imagePath = "http://localhost:8080/" + menuImg;
        System.out.println(imagePath);
        return imagePath;
    }

    @PostMapping("/menuWrite")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadFile(@ModelAttribute MenuDto menuDto,
                                             @RequestParam(value = "uploadImg", required = false) List<MultipartFile> uploadImgs) throws Exception {
        System.out.println(uploadImgs);
        System.out.println(menuDto);
        List <String> files = new ArrayList<>();
        Path uploadDir = Paths.get(uploadPath + "/menu/mImg/");
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

        menuDto.setMenuImg(filesJson);

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

        return menuBuy;
    }

    @PutMapping("/replyReport")
    @CrossOrigin(origins = "*")
    public void replyReport(int replyNo) {
        menuService.replyReport(replyNo);
    }

    @PutMapping("/replyLike")
    @CrossOrigin(origins = "*")
    public void replyLike(int replyNo) {
        menuService.replyLike(replyNo);
    }

    @PutMapping("/menuLike")
    @CrossOrigin(origins = "*")
    public void menuLike(int menuNo) {
        menuService.menuLike(menuNo);
    }

    @PutMapping("/plusCart")
    @CrossOrigin(origins = "*")
    public void plusCart(@RequestBody CartDto cartDto) {
        System.out.println("플러스" + cartDto);
        menuService.plusCart(cartDto);
    }

    @PutMapping("/minusCart")
    @CrossOrigin(origins = "*")
    public void minusCart(@RequestBody CartDto cartDto) {
        System.out.println("마이너스" + cartDto);
        menuService.minusCart(cartDto);
    }

    @DeleteMapping("/deleteCart")
    @CrossOrigin(origins = "*")
    public void deleteCart(int cartId) {
        System.out.println("딜리트" + cartId);
        menuService.deleteCart(cartId);
    }

    @GetMapping("/buyUser")
    public List<AdminDto> buyUser(int userNo){

        return menuService.buyUser(userNo);
    }

}
