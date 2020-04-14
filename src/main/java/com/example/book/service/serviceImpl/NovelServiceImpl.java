package com.example.book.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.po.Novel;
import com.example.book.entity.po.NovelAuthor;
import com.example.book.entity.to.AuthorTO;
import com.example.book.entity.vo.NovelAuthorBaseVO;
import com.example.book.entity.vo.NovelAuthorVo;
import com.example.book.entity.vo.NovelDetailVO;
import com.example.book.entity.vo.NovelSaveVO;
import com.example.book.mapper.NovelAuthorMapper;
import com.example.book.mapper.NovelMapper;
import com.example.book.service.NovelService;
import com.example.book.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private NovelAuthorMapper novelAuthorMapper;


    @Override
    public NovelDetailVO getNovelListById(String id) {
        //首先，返回类型不应该是Novel，应该自定义一个类，用作返回前端的数据封装类
        //还有，文档里说，要返回一个作者名称字段
        //数据库里的小说表没有作者名称字段的，所以就需要去有作者信息的平台拿数据
        //这里还需要调我的接口
        //我先给你展示下怎么转换status
        //就是从一个类的属性转换为另一个类的属性
        Novel novel=novelMapper.selectById(id);
        NovelDetailVO novelDetailVO = new NovelDetailVO();
        novelDetailVO.setId(novel.getId());
        novelDetailVO.setName(novel.getName());
        novelDetailVO.setStatus(novel.getStatus() == 0 ? "连更" : "已完结");
        //好了，之后就只需要作者名称了，你可以调我那个接口，
        // 拿到作者code和作者名称的集合，然后for循环遍历筛一下拿出来就好了
        String url="http://127.0.0.1:9090/api/getAuthors";
        String response=restTemplateUtil.get(url,String.class,null);
        List<AuthorTO> authorTOS= JSONArray.parseArray(response,AuthorTO.class);
        //你这个拿错了，你是用小说的code去匹配作者的code，当然匹配不到，
        // 要用作者的code去匹配作者的code，
        // 首先你还需要查询出小说和作者的关联表数据，然后用那个数据去匹配
        List<NovelAuthor> novelAuthors= novelAuthorMapper.selectList(new QueryWrapper<>());
        NovelAuthorBaseVO novelAuthorBaseVO=new NovelAuthorBaseVO();
        for (NovelAuthor novelAuthor:novelAuthors){
            if (novel.getCode().equalsIgnoreCase(novelAuthor.getNovelCode())){
                novelAuthorBaseVO.setAuthorCode(novelAuthor.getAuthorCode());
                break;
            }
        }
        for (AuthorTO authorTO:authorTOS){
            if (authorTO.getCode().equalsIgnoreCase(novelAuthorBaseVO.getAuthorCode())){
                novelDetailVO.setAuthorName(authorTO.getName());
                break;
            }
        }
        return novelDetailVO;
    }

    @Override
    public boolean insertNovel(NovelSaveVO novelSaveVO) {
        // 向小说表插入小说数据
        Novel novel = buildNovel(novelSaveVO);
        int result = novelMapper.insert(novel);
        if (result < 0){
            return false;
        }
        // 向小说和作者表插入数据
        NovelAuthor novelAuthor = buildNovelAuthor(novelSaveVO);
        result = novelAuthorMapper.insert(novelAuthor);
        if (result < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateNovel(NovelSaveVO novelSaveVO) {
        Novel novel=buildNovel(novelSaveVO);
        boolean result = updateById(novel);
        NovelAuthor novelAuthor=buildNovelAuthor(novelSaveVO);
        // 先删除原有关联数据，根据小说code删除
        int count = novelAuthorMapper.delete(new QueryWrapper<NovelAuthor>().eq("novelCode", novelAuthor.getNovelCode()));
        if (count < 0){
            return false;
        }
        count = novelAuthorMapper.insert(novelAuthor);
        if (count < 0){
            return false;
        }
        return result;
    }

    @Override
    public boolean deleteNovel(String id) {

        Novel novel = novelMapper.selectById(id);
        int count=novelAuthorMapper.delete(new QueryWrapper<NovelAuthor>().eq("novelCode",novel.getCode()));
        if (count<0){
            return false;
        }
        count=novelMapper.deleteById(id);
        if (count<0){
            return false;
        }
        return true;
    }

    /**
     * 封装保存小说表实体类
     * @param novelSaveVO
     * @return
     */
    private Novel buildNovel(NovelSaveVO novelSaveVO){
        Novel novel = new Novel();
        novel.setId(StringUtils.isBlank(novelSaveVO.getId()) ?
                UUID.randomUUID().toString() : novelSaveVO.getId());
        novel.setCode(novelSaveVO.getCode());
        novel.setName(novelSaveVO.getName());
        novel.setStatus(novelSaveVO.getStatus());
        return novel;
    }

    /**
     * 封装保存小说与作者表实体类
     * @param novelSaveVO
     * @return
     */
    private NovelAuthor buildNovelAuthor(NovelSaveVO novelSaveVO){
        NovelAuthor novelAuthor = new NovelAuthor();
        novelAuthor.setAuthorCode(novelSaveVO.getAuthorCode());
        novelAuthor.setNovelCode(novelSaveVO.getCode());
        return novelAuthor;
    }
}
