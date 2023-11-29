package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.dto.CategoryDTO;
import com.hao.domain.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-09 01:00:34
 */

@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> listAllCategoryDTO();

}
