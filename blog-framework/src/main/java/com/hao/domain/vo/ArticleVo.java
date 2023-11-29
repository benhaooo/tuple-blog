package com.hao.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    private Long id;

    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //分类名
    private String categoryName;
    private List<String> tagNameList;
    //缩略图
    private String thumbnail;
    //状态（0已发布，1草稿）
    private String status;
    //文章类型 0原创 1转载 2翻译
    private String type;
    //原文链接
    private String originalUrl;

}
