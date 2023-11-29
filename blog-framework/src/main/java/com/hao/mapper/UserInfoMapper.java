package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.entity.User;
import com.hao.domain.entity.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
