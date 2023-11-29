package com.hao.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    //评论内容
    private String content;

    private Long createBy;

    //昵称
    private String nickName;
    //头像
    private String avatar;

    //真实地址
    private String ipLocation;

    private Date createTime;

    private List<ReplyDTO> replyDTOList;

}
