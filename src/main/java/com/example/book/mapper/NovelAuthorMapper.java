package com.example.book.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.book.entity.po.NovelAuthor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NovelAuthorMapper extends BaseMapper<NovelAuthor> {
}
