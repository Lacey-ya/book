package com.example.book.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.book.entity.po.Novel;
import com.example.book.mapper.NovelMapper;
import com.example.book.service.NovelService;
import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
}
