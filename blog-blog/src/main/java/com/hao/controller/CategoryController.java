package com.hao.controller;


import com.hao.domain.ResponseResult;
import com.hao.domain.dto.CategoryQueryDTO;
import com.hao.domain.entity.Category;
import com.hao.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "所有分类列表")
    @GetMapping("/listAll")
    public ResponseResult listAllCategoryDTO() {
        return categoryService.listAllCategoryDTO();
    }


    @GetMapping("/admin/list")
    public ResponseResult listCategory(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, CategoryQueryDTO categoryQueryDTO) {
        return categoryService.listCategory(pageNum, pageSize, categoryQueryDTO);
    }
    @GetMapping("/admin/listAll")
    public ResponseResult listAllCategory() {
        return categoryService.listAllCategoryDTO();
    }

    @PostMapping("/admin")
    public ResponseResult insertCategory(@RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseResult deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getCategory(@PathVariable Long id) {

        return categoryService.getCategory(id);
    }

    @PutMapping("/admin")
    public ResponseResult updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);


    }
}