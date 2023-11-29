package com.hao.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hao.domain.vo.TagVO;
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
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private String isComment;
    private String type;
    private String originalUrl;
    private Long viewCount;

    private Long likeCount;
    private Boolean isLiked = false;

    private Long createBy;
    private Date createTime;
    private Date updateTime;

    private Long categoryId;
    private String categoryName;
    private List<TagVO> tagList;

}
