package com.hao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Like;

public interface LikeService extends IService<Like> {

    ResponseResult pointLike(Long article_id);

}
