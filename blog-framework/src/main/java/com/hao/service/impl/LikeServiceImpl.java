package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Like;
import com.hao.enums.AppHttpCodeEnum;
import com.hao.mapper.LikeMapper;
import com.hao.service.LikeService;
import com.hao.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


//    toggle
    @Override
    public ResponseResult pointLike(Long article_id) {
        LambdaQueryWrapper<Like> eq = new LambdaQueryWrapper<Like>().eq(Like::getArticle_id, article_id).eq(Like::getCreateBy, SecurityUtils.getUserId());
        int count = likeMapper.selectCount(eq);

        if(count==0){
            likeMapper.insert(Like.builder().article_id(article_id).build());
            return ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.POINT_LIKE);

        }else{
            likeMapper.delete(eq);
            return ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.CANCEL_LIKE);
        }


    }
}
