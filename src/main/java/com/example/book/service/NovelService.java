package com.example.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.book.entity.po.Novel;
import com.example.book.entity.vo.NovelDetailVO;
import com.example.book.entity.vo.NovelSaveVO;


public interface NovelService extends IService<Novel> {

    /**
     * 根据ID获取小说所有信息
     * @return
     */
    NovelDetailVO getNovelListById(String id);

    /**
     * 新增小说和作者相关数据
     * @param novelSaveVO 小说和作者数据
     * @return
     */
    boolean insertNovel(NovelSaveVO novelSaveVO);

    /**
     * 修改小说和作者相关数据
     * @param novelSaveVO 小说和作者数据
     * @return
     */
    boolean updateNovel(NovelSaveVO novelSaveVO);


    boolean deleteNovel(String id);

}
