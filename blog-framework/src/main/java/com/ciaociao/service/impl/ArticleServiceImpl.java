package com.ciaociao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciaociao.constant.SystemConstants;
import com.ciaociao.mapper.ArticleMapper;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Article;
import com.ciaociao.model.vo.HotArticleVo;
import com.ciaociao.service.ArticleService;
import com.ciaociao.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author CIA0CIA0
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2023-09-01 16:34:21
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articleList = page.getRecords();
        //bean拷贝
//        ArrayList<HotArticleVo> hotArticleVoList = new ArrayList<>();
//        for (Article article : articleList) {
//            HotArticleVo hotArticleVo = new HotArticleVo();
//            BeanUtils.copyProperties(article,hotArticleVo);
//            hotArticleVoList.add(hotArticleVo);
//        }
        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVoList);
    }
}




