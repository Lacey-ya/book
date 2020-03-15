package com.example.book.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.NovelAuthorTo;

import java.util.List;

public interface NovelAuthorService extends IService<NovelAuthor> {

    /**
     *获取所有小说名称和作者代码
     * @return
     */
    List<NovelAuthorTo> getNovelAuthorList();

}
