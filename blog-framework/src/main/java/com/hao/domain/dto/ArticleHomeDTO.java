package com.hao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleHomeDTO {
    private Long id;

    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;

    private Date createTime;

    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //标签集合
    private List<TagDTO> tagDTOList;
}
