package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 19:31:06
 */
public interface CommentService extends IService<Comment> {
    ResponseResult selectList(Long articleId);

    ResponseResult publish(Comment comment);
}
