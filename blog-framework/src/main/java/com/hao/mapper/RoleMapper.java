package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleKeyByUserId(Long userId);

}
