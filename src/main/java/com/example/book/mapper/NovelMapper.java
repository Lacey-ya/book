package com.example.book.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.book.entity.po.Novel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {
}
