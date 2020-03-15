package com.example.book.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.service.NovelAuthorService;
import org.springframework.stereotype.Service;

@Service
public class NovelAuthorServiceImpl extends ServiceImpl<NovelAuthorMapper, NovelAuthor> implements NovelAuthorService {

}
