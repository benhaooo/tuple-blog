package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author benhao
 * @since 2022-12-07 16:49:51
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
