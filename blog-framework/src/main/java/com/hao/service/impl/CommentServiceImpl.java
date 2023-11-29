package com.hao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Comment;
import com.hao.domain.vo.CommentVO;
import com.hao.enums.AppHttpCodeEnum;
import com.hao.exception.SystemException;
import com.hao.mapper.CommentMapper;
import com.hao.service.CommentService;
import com.hao.service.UserService;
import com.hao.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 评论表(Comment)表服务实现类
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseResult selectList(Long articleId) {
//        获取根评论
        List<CommentVO> roots = commentMapper.selectRootList(articleId);
//        获取子评论
        List<CommentVO> children = commentMapper.selectChildList(articleId);

        for (CommentVO root : roots) {
            combineChildren(root, children);
        }
        return ResponseResult.okResult(roots);
    }

    //    联系父子关系
    private void combineChildren(CommentVO comment, List<CommentVO> children) {

        ArrayList<CommentVO> list = new ArrayList<>();
//        for (CommentVO child : children) {
//        使用iterator实现遍历过程中删除元素
        for (Iterator<CommentVO> iterator = children.iterator(); iterator.hasNext(); ) {
            CommentVO child = iterator.next();
            if (child.getRootId() == comment.getId()) {
                list.add(child);
//                list.remove(child);
                iterator.remove();
//                combineChildren(child, children);
            }

        }
        comment.setChildren(list);

    }


    @Override
    public ResponseResult publish(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }
}
