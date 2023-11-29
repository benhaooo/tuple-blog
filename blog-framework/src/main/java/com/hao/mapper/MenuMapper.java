package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author benhao
 * @since 2022-12-07 16:49:50
 */

@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
