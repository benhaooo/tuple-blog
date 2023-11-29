package com.hao.domain.dto;


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
public class ArticleBackDTO {
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private String isTop;
    private String type;
    private String status;
    private String isComment;
    private Date createTime;

    private Long categoryId;
    private Long categoryName;
    private List<TagVO> tagVOList;
}
