package com.example.book.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.book.entity.to.NovelAuthorTo;
import com.example.book.entity.vo.NovelAuthorVo;
import com.example.book.service.NovelAuthorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "自学项目",tags = "mybatis plus3自学项目")
@RestController
@RequestMapping("/api")
public class NovelAuthorController {

    @Autowired
    private NovelAuthorService novelAuthorService;


    @ApiOperation(value = "获取小说信息列表",notes = "获取所有小说信息列表")
    @ApiImplicitParam(name = "novelName",value = "姓名",required = false,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/getNovelAuthor")
    public String getNovelAuthor(){
        List<NovelAuthorTo> novelAuthorTos=novelAuthorService.getNovelAuthors();
        return JSON.toJSONString(novelAuthorTos);
    }

    @ApiOperation(value = "获取作者与小说信息列表",notes = "获取所有作者与小说关联信息列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/getNovelAuthorTable")
    public String getNovelAuthorTable(){
        List<NovelAuthorVo> novelAuthorVos=novelAuthorService.getNovelAuthorName();
        return JSON.toJSONString(novelAuthorVos);
    }
}
