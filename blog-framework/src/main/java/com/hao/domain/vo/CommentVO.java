package com.hao.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Long id;
    private Long rootId;
    private Long parentId;
    private String content;
    private String ipLocation;
    private Date createTime;

    //    评论方
    private Long senderId;
    private String senderNickName;
    private String senderAvatar;
    //   被评论方
    private Long parentUserId;
    private String parentUserNickName;
    private String parentUserAvatar;

    private ArrayList<CommentVO> children;

}

