package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.domain.dto.ArticleBackDTO;
import com.hao.domain.dto.ArticleQueryDTO;
import com.hao.domain.entity.Article;
import com.hao.domain.vo.ArticleVo;
import com.hao.domain.vo.ConditionVO;
import com.hao.service.ArticleService;
import com.hao.service.LikeService;
import com.hao.utils.BeanCopyUtils;
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


    @ApiOperation(value = "主页文章列表")
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @ApiOperation(value = "根据id查找文章")
    @GetMapping("/{id}")
    public ResponseResult getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }


    @PostMapping("/save")
    public ResponseResult saveOrUpdateArticle(@RequestBody ArticleVo articleVo) {
        return articleService.saveOrUpdateArticle(articleVo);
    }

    @GetMapping("/admin/list")
    public ResponseResult listArticle(ConditionVO condition) {
        return articleService.listArticle(condition);
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


    @PostMapping("/admin")
    public ResponseResult insertArticle(@RequestBody ArticleBackDTO article) {
        return articleService.insertArticle(article);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PutMapping("/admin")
    public ResponseResult updateArticle(@RequestBody ArticleBackDTO article) {
        return articleService.updateArticle(article);

    }

    @PutMapping("/insert")
    public ResponseResult insert(@RequestBody ArticleBackDTO articleBackDTO) {
        Article article = BeanCopyUtils.copyBean(articleBackDTO, Article.class);
        return ResponseResult.okResult(articleService.save(article));

    }

    @ApiOperation(value = "根据文章id点赞")
    @PutMapping("/like/{id}")
    public ResponseResult pointLike(@PathVariable Long id){
        return likeService.pointLike(id);
    }

}
