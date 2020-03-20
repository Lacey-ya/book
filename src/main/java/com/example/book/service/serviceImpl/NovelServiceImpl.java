package com.example.book.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.po.Novel;
import com.example.book.entity.to.NovelTo;
import com.example.book.mapper.NovelMapper;
import com.example.book.service.NovelService;
import com.example.book.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private NovelMapper novelMapper;

    @Override
    public List<NovelTo> getNovelList() {
        List<Novel> novels=novelMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isEmpty(novels)){
            return new ArrayList<>();
        }
        List<NovelTo> novelTos=new ArrayList<>();
        for (Novel novel:novels){
            NovelTo novelTo=new NovelTo();
            novelTo.setCode(novel.getCode());
            novelTo.setName(novel.getName());
            novelTos.add(novelTo);
        }
        return novelTos;
    }
}
