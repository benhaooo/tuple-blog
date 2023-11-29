package com.hao.domain.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author benhao
 * @since 2022-11-27 14:19:45
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article_tag")
public class ArticleTag {
    //文章id
    private Long articleId;
    //标签
    private Long tagId;


}
