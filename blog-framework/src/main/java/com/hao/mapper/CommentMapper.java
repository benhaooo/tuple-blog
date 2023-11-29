package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.dto.CommentDTO;
import com.hao.domain.entity.Comment;
import com.hao.domain.vo.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 评论表(Comment)表数据库访问层
 */

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVO> selectRootList(Long blogId);
    List<CommentVO> selectChildList(Long blogId);
}
