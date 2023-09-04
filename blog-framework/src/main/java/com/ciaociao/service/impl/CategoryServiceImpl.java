package com.ciaociao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciaociao.constant.SystemConstants;
import com.ciaociao.mapper.CategoryMapper;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Article;
import com.ciaociao.model.domain.Category;
import com.ciaociao.model.vo.CategoryVO;
import com.ciaociao.service.ArticleService;
import com.ciaociao.service.CategoryService;
import com.ciaociao.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CIA0CIA0
 * @description 针对表【category(分类表)】的数据库操作Service实现
 * @createDate 2023-09-02 20:43:57
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询状态为已发布的文章列表
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);
        //获取文章分类id,并去重
        List<Long> categoryIdList = articleList.stream()
                .map(Article::getCategoryId)
                .distinct()
                .collect(Collectors.toList());
        //查询分类信息
        List<Category> categoryList = listByIds(categoryIdList);
        List<Category> normalCategoryList = categoryList.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVO> categoryVOList = BeanCopyUtils.copyBeanList(normalCategoryList, CategoryVO.class);
        return ResponseResult.okResult(categoryVOList) ;
    }
}




