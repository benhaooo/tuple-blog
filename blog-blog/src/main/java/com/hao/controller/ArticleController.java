package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.domain.vo.ArticleVo;
import com.hao.domain.vo.ConditionVO;
import com.hao.service.ArticleService;
import com.hao.service.LikeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LikeService likeService;


    @ApiOperation(value = "根据id查找文章")
    @GetMapping("/{id}")
    public ResponseResult getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getAdminArticleById(@PathVariable Long id) {
        return articleService.getAdminArticleById(id);
    }


    @PostMapping("/crawler/save")
    public ResponseResult saveOrUpdateArticle(@RequestBody ArticleVo articleVo) {
        return articleService.saveOrUpdateArticle(articleVo);
    }

    @GetMapping("/admin/list/condition")
    public ResponseResult listArticle(ConditionVO condition) {
        return articleService.listAdminArticleByCondition(condition);
    }

    @ApiOperation(value = "根据condition(条件)查找文章列表")
    @GetMapping("/list/condition")
    public ResponseResult listArticleByCondition(ConditionVO condition) {
        return articleService.listArticleByCondition(condition);
    }

    @ApiOperation(value = "所有文章列表(归档)")
    @GetMapping("/list/archive")
    public ResponseResult listArticleArchive() {
        return articleService.listArticleArchive();
    }


    @ApiOperation(value = "根据文章id点赞")
    @PutMapping("/like/{id}")
    public ResponseResult pointLike(@PathVariable Long id){
        return likeService.pointLike(id);
    }

}
