package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.ArticleBackDTO;
import com.hao.domain.dto.ArticleQueryDTO;
import com.hao.domain.entity.Article;
import com.hao.domain.vo.ArticleVo;
import com.hao.domain.vo.ConditionVO;

public interface ArticleService extends IService<Article> {

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult saveOrUpdateArticle(ArticleVo articleVo);

    ResponseResult getArticleById(Long id);

    ResponseResult listArticle(ConditionVO condition);

    ResponseResult listArticleByCondition(ConditionVO condition);

    ResponseResult listArticleArchive();

    ResponseResult insertArticle(ArticleBackDTO article);

    ResponseResult deleteArticle(Long id);

    ResponseResult getArticle(Long id);

    ResponseResult updateArticle(ArticleBackDTO article);

}
