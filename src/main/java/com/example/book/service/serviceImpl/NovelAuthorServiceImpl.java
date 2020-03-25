package com.example.book.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.po.Novel;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.AuthorTO;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.entity.vo.NovelAuthorBaseVO;
import com.example.book.entity.vo.NovelAuthorVo;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.mapper.NovelMapper;
import com.example.book.service.NovelAuthorService;
import com.example.book.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<NovelAuthor> novelAuthors = novelAuthorMapper.selectList(new QueryWrapper<>());
        List<NovelAuthorBaseVO> baseVOS = new ArrayList<>();
        // 先将小说表数据和小说与作者的关联表数据整合在一起
        // 好为下面的数据匹配做准备
        for (Novel novel : novels){
            NovelAuthorBaseVO baseVO = new NovelAuthorBaseVO();
            for (NovelAuthor novelAuthor : novelAuthors){
                if (novel.getCode().equalsIgnoreCase(novelAuthor.getNovelCode())){
                    baseVO.setAuthorCode(novelAuthor.getAuthorCode());
                    break;
                }
            }
            baseVO.setStatus(novel.getStatus());
            baseVO.setNovelName(novel.getName());
            baseVOS.add(baseVO);
        }
        // 将上面整合在一起的数据与调接口的数据放一起匹配
        // 并且赋值给前端的数据封装类
        for (AuthorTO authorTO:authorTOS){
            NovelAuthorVo novelAuthorVo=new NovelAuthorVo();
            for (NovelAuthorBaseVO baseVO:baseVOS){
                if (authorTO.getCode().equalsIgnoreCase(baseVO.getAuthorCode())){
                    novelAuthorVo.setStatus(baseVO.getStatus() == 0 ? "连更" : "已完结");
                    novelAuthorVo.setNovelName(baseVO.getNovelName());
                    break;
                }
            }
            novelAuthorVo.setAuthorName(authorTO.getName());
            novelAuthorVos.add(novelAuthorVo);
        }
        return novelAuthorVos;
    }
}
