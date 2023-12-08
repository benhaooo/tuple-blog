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

    private String title;
    private String content;
    private String categoryName;
    private List<String> tagNameList;
    private String thumbnail;
    private String status;
    private String type;
    private String originalUrl;

}
