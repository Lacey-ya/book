package com.example.book.controller;

import com.alibaba.fastjson.JSON;
import com.example.book.entity.vo.NovelDetailVO;
import com.example.book.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @GetMapping("getNovelDetail")
    public String getNovelListById(@RequestParam("id") String id){
        NovelDetailVO novelDetailVO=novelService.getNovelListById(id);
        return JSON.toJSONString(novelDetailVO);
    }

}
