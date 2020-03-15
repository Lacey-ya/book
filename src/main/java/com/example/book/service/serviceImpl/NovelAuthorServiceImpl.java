package com.example.book.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.book.config.RestTemplateConfig;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.service.NovelAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelAuthorServiceImpl extends ServiceImpl<NovelAuthorMapper, NovelAuthor> implements NovelAuthorService {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private NovelAuthorMapper novelAuthorMapper;


    @Override
    public List<NovelAuthorTo> getNovelAuthorList() {
        return null;
    }
}
