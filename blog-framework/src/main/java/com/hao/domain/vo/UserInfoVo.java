package com.hao.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVo {

    private Long id;

    private String nickName;
    private String avatar;
    private String webSite;
    private String intro;
    private String email;

}
