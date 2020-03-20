package com.example.book.controller;

import com.alibaba.fastjson.JSON;
import com.example.book.entity.to.NovelTo;
import com.example.book.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @GetMapping("/getNovels")
    public String getNovels(){
        List<NovelTo> novelTos=novelService.getNovelList();
        return JSON.toJSONString(novelTos);
    }
}
