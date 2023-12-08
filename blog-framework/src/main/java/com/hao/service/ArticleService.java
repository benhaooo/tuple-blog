package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Article;
import com.hao.domain.vo.ArticleVo;
import com.hao.domain.vo.ConditionVO;

public interface ArticleService extends IService<Article> {


    ResponseResult saveOrUpdateArticle(ArticleVo articleVo);

    ResponseResult getArticleById(Long id);

    ResponseResult getAdminArticleById(Long id);

    ResponseResult listAdminArticleByCondition(ConditionVO condition);

    ResponseResult listArticleByCondition(ConditionVO condition);

    ResponseResult listArticleArchive();


}
