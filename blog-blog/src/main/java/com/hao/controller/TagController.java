package com.hao.controller;


import com.hao.domain.ResponseResult;
import com.hao.domain.dto.TagQueryDTO;
import com.hao.domain.entity.Tag;
import com.hao.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/admin/list")
    public ResponseResult listTag(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, TagQueryDTO tagQueryDTO) {
        return tagService.listTag(pageNum, pageSize, tagQueryDTO);
    }
    @GetMapping("/admin/listAll")
    public ResponseResult listAllTag() {
        return tagService.listAllTag();
    }

    @ApiOperation(value = "所有标签列表")
    @GetMapping("/listAll")
    public ResponseResult listAllTagVO() {
        return tagService.listAllTagVO();
    }

    @PostMapping("/admin")
    public ResponseResult insertTag(@RequestBody Tag tag){
        return tagService.insertTag(tag);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getTag(@PathVariable Long id) {
        return tagService.getTag(id);
    }
    @PutMapping("/admin")
    public ResponseResult updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }
}
