package com.hao.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {
    private Long userId;
    private String userName;
    private String nickName;
    private String name;
    private String phone;
    private String email;
    private Date birthday;
    private String identity;
    private String organization;
    private String skills;
    private String introduction;
}
