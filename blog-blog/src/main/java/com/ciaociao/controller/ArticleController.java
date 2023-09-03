package com.ciaociao.controller;

import com.ciaociao.model.ResponseResult;
import com.ciaociao.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        return articleService.getHotArticleList();
    }
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.getArticleList(pageNum,pageSize,categoryId);
    }
    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);

    }
}
