package com.ciaociao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Category;

/**
* @author CIA0CIA0
* @description 针对表【category(分类表)】的数据库操作Service
* @createDate 2023-09-02 20:43:57
*/
public interface CategoryService extends IService<Category> {
    /**
     * 获取文章分类列表
     * @return
     */
    ResponseResult getCategoryList();
}
