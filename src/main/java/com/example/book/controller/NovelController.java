package com.example.book.controller;

import com.alibaba.fastjson.JSON;
import com.example.book.entity.vo.NovelDetailVO;
import com.example.book.entity.vo.NovelSaveVO;
import com.example.book.service.NovelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @ApiOperation(value = "获取小说详情接口",notes = "根据ID获取小说详情接口")
    @ApiImplicitParam(name = "id",value = "id名称",required = true,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("getNovelDetail")
    public String getNovelListById(@RequestParam("id") String id){
        NovelDetailVO novelDetailVO=novelService.getNovelListById(id);
        return JSON.toJSONString(novelDetailVO);
    }

    @ApiOperation(value = "新增小说和作者数据接口",notes = "新增小说和作者数据接口")
    @ApiImplicitParam(name = "novelSaveVO",value = "新增的数据",required = true,dataType = "NovelSaveVO",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PostMapping("/novel")
    public void insertNovel(@RequestBody NovelSaveVO novelSaveVO){
        novelService.insertNovel(novelSaveVO);
    }

    @ApiOperation(value = "修改小说和作者数据接口",notes = "修改小说和作者数据接口")
    @ApiImplicitParam(name = "novelSaveVO",value = "修改的数据",required = true,dataType = "NovelSaveVO",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PutMapping("/novel")
    public void updateNovel(@RequestBody NovelSaveVO novelSaveVO){
        novelService.updateNovel(novelSaveVO);
    }

    @ApiOperation(value = "删除小说和作者数据接口",notes = "删除小说和作者数据接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @DeleteMapping("/novel")
    public void deleteNovel(@RequestParam("id") String id){
        novelService.deleteNovel(id);
    }

}
