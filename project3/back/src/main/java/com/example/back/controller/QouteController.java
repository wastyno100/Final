package com.example.back.controller;

import com.example.back.dto.QoutedDto;
import com.example.back.service.QouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
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
public class QouteController {
    @Autowired
    private QouteService qouteService;

    @GetMapping("/qouteList")
    public List<QouteDto> qouteData(@RequestBodyt qouteNo){
        
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@")
    
    return  qouteService.qouteData(qouteNo);
    }
   
    

    @DeleteMapping("/qouteDelete")
    public void qouteDelete(int qouteNo) {
        qouteService.qouteDelete(qouteNo);
    }
}
