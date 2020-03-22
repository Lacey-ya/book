package com.example.book.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.po.Novel;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.AuthorTO;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.entity.to.NovelTo;
import com.example.book.entity.vo.NovelAuthorVo;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.mapper.NovelMapper;
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

    @Autowired
    private NovelMapper novelMapper;

    @Override
    public List<NovelAuthorTo> getNovelAuthors() {
        return novelAuthorMapper.getNovelAuthors();
    }

    @Override
    public List<NovelAuthorVo> getNovelAuthorName() {
        //调用其他平台接口
        String url="http://127.0.0.1:9090/api/getAuthors";  //平台接口地址
        String response=restTemplateUtil.get(url,String.class,null);    //调用接口获取数据
        List<AuthorTO> authorTOS=JSONArray.parseArray(response,AuthorTO.class);

        //将调用平台拿到的数据反序列化，赋给类的对象，便于操作
        List<NovelAuthorVo> novelAuthorVos=new ArrayList<>();
        List<Novel> novels=novelMapper.selectList(new QueryWrapper<>());
        for (AuthorTO authorTO:authorTOS){
            NovelAuthorVo novelAuthorVo=new NovelAuthorVo();
            for (Novel novel:novels){
                if (authorTO.getCode().equalsIgnoreCase(novel.getCode())){
                    novelAuthorVo.setNovelName(novel.getName());
                    break;
                }
            }
            novelAuthorVo.setAuthorName(authorTO.getName());
            novelAuthorVos.add(novelAuthorVo);
        }
        return novelAuthorVos;
    }
}
