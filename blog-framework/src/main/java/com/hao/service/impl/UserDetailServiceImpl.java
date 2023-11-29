package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.constants.SystemConstants;
import com.hao.domain.entity.LoginUser;
import com.hao.domain.entity.User;
import com.hao.enums.UserStatusEnum;
import com.hao.exception.SystemException;
import com.hao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName, username);
        userLambdaQueryWrapper.last(SystemConstants.MP_SQL_LAST);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        //判断是否查到用户  如果没查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        if(UserStatusEnum.ABNORMAL.getStatus().equals(user.getStatus())){
            throw new RuntimeException("用户状态异常");
        }
        //返回用户信息
        return new LoginUser(user);
    }
}
