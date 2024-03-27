package com.example.back.controller;

import com.example.back.dto.QouteDto;
import com.example.back.service.QouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class QouteController {

    private final QouteService qouteService;

    @Autowired
    public QouteController(QouteService qouteService) {
        this.qouteService = qouteService;
    }

    @GetMapping("/qouteList")
    public List<QouteDto> getAllQoute() {
        log.info("싯리스트 들어갔어");
        System.out.println(qouteService.qouteList());
        return qouteService.qouteList();
    }
    @GetMapping("/qouteListQouteCate")
    public List<QouteDto> getFishQoute(@RequestParam(name="qouteCate") String qouteCate){
        log.info("생선카테고리 들어갔어");
        return qouteService.qouteListQouteCate();
    }

    @DeleteMapping("/qouteDelete")
    public void qouteDelete(int qouteNo) {

        qouteService.qouteDelete(qouteNo);
    }
}