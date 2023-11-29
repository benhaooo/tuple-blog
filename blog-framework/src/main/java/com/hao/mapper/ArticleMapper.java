package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.dto.ArticleBackDTO;
import com.hao.domain.dto.ArticleDTO;
import com.hao.domain.entity.Article;
import com.hao.domain.vo.ArticlePreviewDTO;
import com.hao.domain.vo.ConditionVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    List<Object> articleList(@Param("current") Integer current, @Param("size") Integer size, @Param("categoryId") Long categoryId);

    ArticleDTO getArticleById(@Param("id") Long id);

    List<ArticlePreviewDTO> listArticleByCondition(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    Long countArticle(ConditionVO condition);


//    {id={id=#,vieCount=#}},{...}
//    键为String
    @MapKey("id")
    List<Map<Long,Long>> listViewCount();

}
