package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.constants.SystemConstants;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.User;
import com.hao.domain.entity.UserInfo;
import com.hao.domain.vo.UserInfoVo;
import com.hao.mapper.UserInfoMapper;
import com.hao.mapper.UserMapper;
import com.hao.service.UserService;
import com.hao.utils.BeanCopyUtils;
import com.hao.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 19:51:49
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = userMapper.selectById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(UserInfoVo userInfoVo) {
        User user = BeanCopyUtils.copyBean(userInfoVo, User.class);
        userMapper.updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delUser(String id) {
        userMapper.deleteById(id);
        return null;
    }


    @Override
    public ResponseResult userInfoById() {
        Long user_id = SecurityUtils.getUserId();
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, user_id).last(SystemConstants.MP_SQL_LAST);
        UserInfo userInfo = userInfoMapper.selectOne(lambdaQueryWrapper);
        return ResponseResult.okResult(userInfo);
    }

    @Override
    public ResponseResult updateUserInfoById(UserInfo userInfo) {
        Long user_id = SecurityUtils.getUserId();
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, user_id).last(SystemConstants.MP_SQL_LAST);
        return ResponseResult.okResult(userInfoMapper.update(userInfo,lambdaQueryWrapper));
    }


}
