package com.hao.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleArchiveVO {
    private Long id;
    private String title;
    private String thumbnail;
    private Date createTime;
}
