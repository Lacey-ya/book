package com.example.book.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.NovelAuthorTo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NovelAuthorMapper extends BaseMapper<NovelAuthor> {
    List<NovelAuthorTo> getNovelAuthors();
}
