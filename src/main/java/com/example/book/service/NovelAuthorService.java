package com.example.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.entity.vo.NovelAuthorVo;

import java.util.List;

public interface NovelAuthorService extends IService<NovelAuthor> {

    /**
     *获取所有小说名称和作者代码
     *
     * @return
     */
    List<NovelAuthorTo> getNovelAuthors();

    /**
     * 获取小说名字和作者名字
     * @return
     */
    List<NovelAuthorVo> getNovelAuthorName();


}
