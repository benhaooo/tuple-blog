package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.User;
import com.hao.domain.entity.UserInfo;
import com.hao.domain.vo.UserInfoVo;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 19:51:49
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(UserInfoVo userInfoVo);

    ResponseResult delUser(String id);

    ResponseResult userInfoById();

    ResponseResult updateUserInfoById(UserInfo userInfo);
}
