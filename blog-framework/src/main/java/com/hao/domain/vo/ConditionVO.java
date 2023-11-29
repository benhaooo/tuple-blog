package com.hao.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConditionVO {
    private Long current;
    private Long size;
    private Long categoryId;
    private Long tagId;
    private String keyword;

}
