package com.hao.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserRegisterVo {
    //用户名
    private String userName;

    //密码
    private String password;

    //邮箱
    private String email;

    //验证码
    private String code;

}
