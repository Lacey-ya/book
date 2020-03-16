package com.example.book.controller;

import com.alibaba.fastjson.JSON;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.service.NovelAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NovelAuthorController {

    @Autowired
    private NovelAuthorService novelAuthorService;

    @GetMapping("/getNovelAuthor")
    public String getNovelAuthor(){
        List<NovelAuthorTo> novelAuthorTos=novelAuthorService.getNovelAuthors();
        return JSON.toJSONString(novelAuthorTos);
    }

}
