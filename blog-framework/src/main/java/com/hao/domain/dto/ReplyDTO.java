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
public class ReplyDTO {
    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Integer parentId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    //所回复的目标评论的用户昵称
    private String  toCommentNickname;
    //回复目标评论id
    private Long toCommentId;
    //真实地址
    private String ipLocation;

    private Long createBy;

    //昵称
    private String nickName;
    //头像
    private String avatar;

    private Date createTime;
}
