package com.example.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.book.entity.po.Novel;
import com.example.book.entity.vo.NovelDetailVO;


public interface NovelService extends IService<Novel> {

    /**
     * 根据ID获取小说所有信息
     * @return
     */
    NovelDetailVO getNovelListById(String id);

}
