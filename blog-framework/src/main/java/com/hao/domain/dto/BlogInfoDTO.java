package com.hao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfoDTO {
    private Integer articleCount;
    private Integer tagCount;
    private Integer categoryCount;
}
