package com.hao.domain.dto;


import com.hao.domain.vo.ArticlePreviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePreviewDataDTO {
    private String name;
    private List<ArticlePreviewDTO> articlePreviewDTOList;
}
