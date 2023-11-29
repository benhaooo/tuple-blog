package com.hao.controller;

import com.hao.constants.SystemConstants;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Comment;
import com.hao.service.CommentService;
import com.hao.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "根据文章id查找文章所有评论")
    @GetMapping("/list/{articleId}")
    public ResponseResult commentList(@PathVariable Long articleId) {
        return commentService.selectList(articleId);
    }

    @ApiOperation(value = "发布评论")
    @PostMapping("/publish")
    public ResponseResult publishComment(@RequestBody Comment comment){
        return commentService.publish(comment);
    }

}