package com.example.back.mapper;

import com.example.back.dto.BoardDto;
import com.example.back.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuDto> menuDto();
    List<MenuDto> menuDetail(int menuNo);
}
