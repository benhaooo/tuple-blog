package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.CategoryDTO;
import com.hao.domain.dto.CategoryQueryDTO;
import com.hao.domain.entity.Category;
import com.hao.domain.vo.PageVo;
import com.hao.mapper.CategoryMapper;
import com.hao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-09 01:00:47
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    //后台分类列表
    @Override
    public ResponseResult listAllCategoryDTO() {
        return ResponseResult.okResult(categoryMapper.listAllCategoryDTO());
    }

    @Override
    public ResponseResult listCategory(Integer pageNum, Integer pageSize, CategoryQueryDTO categoryQueryDTO) {
        //分页查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(categoryQueryDTO.getName()), Category::getName, categoryQueryDTO.getName());
        queryWrapper.eq(StringUtils.hasText(categoryQueryDTO.getStatus()), Category::getStatus, categoryQueryDTO.getStatus());
        Page<Category> page = new Page<Category>()
                .setCurrent(pageNum)
                .setSize(pageSize);
        page(page, queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult listAllCategory() {
        List<CategoryDTO> categories = categoryMapper.listAllCategoryDTO();
        return ResponseResult.okResult(categories);
    }

    @Override
    public ResponseResult insertCategory(Category category) {
        categoryMapper.insert(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        categoryMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategory(Long id) {
        Category category = categoryMapper.selectById(id);
        return ResponseResult.okResult(category);
    }

    @Override
    public ResponseResult updateCategory(Category category) {
        categoryMapper.updateById(category);
        return ResponseResult.okResult();
    }
}
