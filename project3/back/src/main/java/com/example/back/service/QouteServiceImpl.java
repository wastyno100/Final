package com.example.back.service;

import com.example.back.dto.QouteDto;
import com.example.back.mapper.QouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//xml파일의 내용을 가져오기 위해 @Mapper 어노테이션을 입력
@service
public class qouteServiveImpl implements QouteServive {
     @Autowired
     private QouteMapper qouteMapper;
     
     @Override
     public List<QouteDto> qouteData(int qouteNo){
          return qouteMapper.qouteData(qouteNo)
     }
     public void qouteDelete(int qouteNo){
          qouteMapper.qouteDelete(qouteNo);
     }

}
