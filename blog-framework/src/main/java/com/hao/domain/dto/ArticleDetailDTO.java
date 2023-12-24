package com.hao.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private Long categoryId;
    private List<Long> tagList;

}
