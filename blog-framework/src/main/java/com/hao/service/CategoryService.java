package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.CategoryQueryDTO;
import com.hao.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-09 01:00:47
 */
public interface CategoryService extends IService<Category> {

    ResponseResult listAllCategoryDTO();

    ResponseResult listCategory(Integer pageNum, Integer pageSize, CategoryQueryDTO categoryQueryDTO);

    ResponseResult listAllCategory();

    ResponseResult insertCategory(Category category);

    ResponseResult deleteCategory(Long id);

    ResponseResult getCategory(Long id);

    ResponseResult updateCategory(Category category);
}
