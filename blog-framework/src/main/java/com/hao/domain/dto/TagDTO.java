package com.hao.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    private Long id;
    private String name;
    private String color;
    private Integer articleCount;
    private Date createTime;
}
