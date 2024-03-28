package com.example.back.controller;

import com.example.back.dto.BoardDto;
import com.example.back.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@RestController
public class BoardController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @GetMapping("/getImage/{imgName}")
    public String getImage(@PathVariable String imgName) throws Exception {
       String imagePath = "/api/" + imgName;
        System.out.println(imagePath);
            return imagePath;
    }

    @GetMapping("/boardList")
    @CrossOrigin(origins = "*")
    public List<BoardDto> board(@RequestParam("boardCate") String boardCate) throws Exception {
        List<BoardDto> boardData = boardService.boardData(boardCate);

        return boardData;
    }

    @GetMapping("/boardDetail")
    @CrossOrigin(origins = "*")
    public List<BoardDto> boardDetail(int boardNo) throws Exception {
        List<BoardDto> boardDetail = boardService.boardDetail(boardNo);

        return boardDetail;
    }

//    @PostMapping("/boardWrite")
//    @CrossOrigin(origins = "*")
//    public void boardWrite(@RequestBody BoardDto boardDto) throws Exception {
//        System.out.println("작성데이터: " + boardDto);
//        boardService.boardWrite(boardDto);
//
//    }

    @PostMapping("/boardWrite")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadFile(@ModelAttribute BoardDto boardDto,
                                             @RequestParam(value = "uploadImg", required = false) List<MultipartFile> uploadImgs) throws Exception {

        List <String> files = new ArrayList<>();
        Path uploadDir = Paths.get(uploadPath + "/boardImg/");

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

            boardDto.setBoardImg(filesJson);

            boardService.boardWrite(boardDto);

            return ResponseEntity.ok("저장완료");
        }

        boardService.boardWrite(boardDto);

        return ResponseEntity.ok("저장완료");
    }

    @PutMapping("/boardUpdate")
    @CrossOrigin(origins = "*")
    public void boardUpdate(@RequestBody BoardDto boardDto) {
        boardService.boardUpdate(boardDto);
    }

    @DeleteMapping("/boardDelete")
    @CrossOrigin(origins = "*")
    public void boardDelete(int boardNo) {
        boardService.boardDelete(boardNo);
    }
}
