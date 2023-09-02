package com.ciaociao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Article;

/**
* @author CIA0CIA0
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2023-09-01 16:34:21
*/
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();
}
