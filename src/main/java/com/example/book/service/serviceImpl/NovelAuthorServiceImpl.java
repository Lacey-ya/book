package com.example.book.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.book.config.RestTemplateConfig;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.service.NovelAuthorService;
import com.example.book.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NovelAuthorServiceImpl extends ServiceImpl<NovelAuthorMapper, NovelAuthor> implements NovelAuthorService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private NovelAuthorMapper novelAuthorMapper;


    @Override
    public List<NovelAuthorTo> getNovelAuthors() {



        return novelAuthorMapper.getNovelAuthors();
    }
}
