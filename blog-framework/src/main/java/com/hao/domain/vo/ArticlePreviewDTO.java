package com.hao.domain.vo;

import com.hao.domain.dto.TagDTO;
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
public class ArticlePreviewDTO {
    private Long id;
    //标题
    private String title;

    private String content;
    //缩略图
    private String thumbnail;

    private Long viewCount;

    private Long likeCount;

    private Long commentCount;

    private Date createTime;

    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //标签集合
    private List<TagVO> tags;

}
