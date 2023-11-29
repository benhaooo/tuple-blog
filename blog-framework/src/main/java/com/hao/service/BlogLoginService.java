package com.hao.service;

import com.hao.domain.ResponseResult;
import com.hao.domain.entity.User;
import com.hao.domain.vo.BlogUserRegisterVo;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult register(BlogUserRegisterVo blogUserRegisterVo);
}
