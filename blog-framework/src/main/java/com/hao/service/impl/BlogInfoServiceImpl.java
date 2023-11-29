package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.BlogInfoDTO;
import com.hao.domain.entity.Article;
import com.hao.enums.ArticleStatusEnum;
import com.hao.mapper.ArticleMapper;
import com.hao.mapper.CategoryMapper;
import com.hao.mapper.TagMapper;
import com.hao.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult getBlogInfo() {
        Integer articleCount = articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getStatus, "1"));
        Integer tagCount = tagMapper.selectCount(null);
        Integer categoryCount = categoryMapper.selectCount(null);
        return ResponseResult.okResult(BlogInfoDTO.builder().articleCount(articleCount).tagCount(tagCount).categoryCount(categoryCount).build());
    }
}
