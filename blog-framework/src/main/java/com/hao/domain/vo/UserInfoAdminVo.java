package com.hao.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAdminVo {
    private Long id;
    private List<String> permissions;
    private String username;
    private String avatar;
}