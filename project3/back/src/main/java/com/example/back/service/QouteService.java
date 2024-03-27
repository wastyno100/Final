package com.example.back.service;

import com.example.back.dto.QouteDto;
import com.example.back.mapper.QouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QouteService {
    private final QouteMapper qouteMapper;
    @Autowired
    public QouteService(QouteMapper qouteMapper){
        this.qouteMapper = qouteMapper;
    }
    public List<QouteDto> qouteList() { return qouteMapper.qouteList();}
    public List<QouteDto> qouteListQouteCate(){
        return qouteMapper.qouteListQouteCate();
    }
    public void qouteDelete(int qouteNo) {
        qouteMapper.qouteDelete(qouteNo);
    }

}

