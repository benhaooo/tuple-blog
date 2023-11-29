package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author benhao
 * @since 2022-11-27 14:19:45
 */
@Repository
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    void deleteByTagId(@Param("tagId") Long tagId);

    void deleteByArticleId(@Param("article_id") Long article_id);

    void insertList(@Param("article_id") Long article_id, @Param("tags") List<Integer> tags);

    List<Integer> tagsIdByArticleId(@Param("article_id") Long article_id);
}
