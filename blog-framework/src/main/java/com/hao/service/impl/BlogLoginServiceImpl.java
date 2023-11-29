package com.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.constants.SystemConstants;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.LoginUser;
import com.hao.domain.entity.User;
import com.hao.domain.entity.UserInfo;
import com.hao.domain.vo.BlogUserLoginVo;
import com.hao.domain.vo.BlogUserRegisterVo;
import com.hao.domain.vo.UserInfoVo;
import com.hao.enums.AppHttpCodeEnum;
import com.hao.exception.SystemException;
import com.hao.mapper.UserInfoMapper;
import com.hao.mapper.UserMapper;
import com.hao.service.BlogLoginService;
import com.hao.utils.BeanCopyUtils;
import com.hao.utils.JwtUtil;
import com.hao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult login(User user) {
        //判断是否填写用户名
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        //进行认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.ERROR_USERNAME_PASSWORD);
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息转成json字符串(可配置自动)存入redis
        redisCache.setCacheObject(SystemConstants.REDIS_TOKEN_PRE + userId, JSON.toJSONString(loginUser));
        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        //SecurityContextHolder同一线程只能获取到对应的数据
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject(SystemConstants.REDIS_TOKEN_PRE + userId);
        return ResponseResult.okResult();
    }

    //注册用户
    @Override
    public ResponseResult register(BlogUserRegisterVo blogUserRegisterVo) {
        //校验验证码
        //校验用户名是否已存在
        User authUserName = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getUserName).eq(User::getUserName, blogUserRegisterVo.getUserName()).last(SystemConstants.MP_SQL_LAST));
        if (Objects.nonNull(authUserName)) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_OCCUPIED);
        }
        //校验邮箱是否被注册
        User authUserEmail = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getEmail).eq(User::getEmail, blogUserRegisterVo.getEmail()).last(SystemConstants.MP_SQL_LAST));
        if (Objects.nonNull(authUserEmail)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        //插入数据
        User user = User.builder()
                .userName(blogUserRegisterVo.getUserName())
                .email(blogUserRegisterVo.getEmail())
                .password(passwordEncoder.encode(blogUserRegisterVo.getPassword()))
                .nickName("用户" + UUID.randomUUID())
                .avatar(SystemConstants.DEFAULT_AVATAR)
                .build();
        userMapper.insert(user);

        //插入成功后原对象id被自动赋值
        //同步user和userInfo表
        UserInfo userInfo = UserInfo.builder().userId(user.getId()).userName(user.getUserName()).nickName(user.getNickName()).email(user.getEmail()).build();
        userInfoMapper.insert(userInfo);

        return ResponseResult.okResult();

    }
}
