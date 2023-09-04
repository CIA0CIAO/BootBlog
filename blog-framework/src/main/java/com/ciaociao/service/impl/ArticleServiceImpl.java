package com.ciaociao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciaociao.constant.SystemConstants;
import com.ciaociao.mapper.ArticleMapper;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Article;
import com.ciaociao.model.domain.Category;
import com.ciaociao.model.vo.ArticleDetailVO;
import com.ciaociao.model.vo.ArticleListVO;
import com.ciaociao.model.vo.HotArticleVO;
import com.ciaociao.model.vo.PageVO;
import com.ciaociao.service.ArticleService;
import com.ciaociao.service.CategoryService;
import com.ciaociao.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CIA0CIA0
 * @description 针对表【article(文章表)】的数据库操作Service实现
 * @createDate 2023-09-01 16:34:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    CategoryService categoryService;
    @Override
    public ResponseResult getHotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
        //bean拷贝
//        ArrayList<HotArticleVo> hotArticleVoList = new ArrayList<>();
//        for (Article article : articleList) {
//            HotArticleVo hotArticleVo = new HotArticleVo();
//            BeanUtils.copyProperties(article,hotArticleVo);
//            hotArticleVoList.add(hotArticleVo);
//        }
        List<HotArticleVO> hotArticleVOList = BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);
        return ResponseResult.okResult(hotArticleVOList);
    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //组合查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (categoryId != null && categoryId > 0) {
            queryWrapper.eq(Article::getCategoryId, categoryId);
        }
        //查询状态为已发布的文章列表
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
        //1.foreach循环版
//        for (Article article : articleList) {
//            Category category = categoryService.getById(article.getCategoryId());
//            category.setName(category.getName());
//        }
        articleList.stream()
                //2.1.普通stream流
//                .map(article -> {
//                    Category category = categoryService.getById(article.getCategoryId());
//                    article.setCategoryName(category.getName());
//                    return article;
//                })
                //2.2.链式编程简化版，须在实体类Article加入@Accessors(chain = true)
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装vo
        List<ArticleListVO> articleListVoList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVO.class);
        PageVO pageVo = new PageVO(articleListVoList, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成VO
        ArticleDetailVO articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVO.class);
        //查询分类id查询分类名称
        Category category = categoryService.getById(article.getCategoryId());
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}




