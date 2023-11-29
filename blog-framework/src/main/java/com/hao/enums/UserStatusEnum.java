package com.hao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    NORMAL("0", "正常"),

    ABNORMAL("1", "异常");

    private final String status;

    private final String desc;
}
