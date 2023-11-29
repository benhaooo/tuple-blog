package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author benhao
 * @since 2022-12-07 16:50:19
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
