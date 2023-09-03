package com.ciaociao.controller;

import com.ciaociao.model.ResponseResult;
import com.ciaociao.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/categoryList")
    public ResponseResult categoryList(){
        return categoryService.getCategoryList();
    }
}
