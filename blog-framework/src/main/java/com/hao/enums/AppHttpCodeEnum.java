package com.hao.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    REQUIRE_USERNAME(5001, "必需填写用户名"),
    ERROR_USERNAME_PASSWORD(5002, "用户名或密码错误"),

    EMAIL_OCCUPIED(5003, "邮箱已被注册"),
    USERNAME_EXIST(5004, "用户名已存在"),

    FILE_TYPE_ERROR(5301, "文件类型错误，请上传图片文件"),
    FILE_UPLOAD_ERROR(5302,"文件上传失败"),

    NEED_LOGIN(50001, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),

    CONTENT_NOT_NULL(506, "评论内容不能为空"),

    LOGIN_ERROR(505, "用户名或密码错误"),

    POINT_LIKE(20001,"点赞成功"),
    CANCEL_LIKE(20002,"取消成功");


    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

