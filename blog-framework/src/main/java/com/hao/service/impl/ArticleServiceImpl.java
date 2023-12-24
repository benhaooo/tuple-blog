package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.constants.SystemConstants;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.*;
import com.hao.domain.entity.*;
import com.hao.domain.vo.*;
import com.hao.enums.ArticleStatusEnum;
import com.hao.mapper.*;
import com.hao.service.ArticleService;
import com.hao.service.ArticleTagService;
import com.hao.service.CategoryService;
import com.hao.service.TagService;
import com.hao.utils.BeanCopyUtils;
import com.hao.utils.PageUtils;
import com.hao.utils.RedisCache;
import com.hao.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private LikeMapper likeMapper;


    /**
     * 添加文章
     *
     * @param articleVo
     * @return
     */
    @Override
    public ResponseResult saveOrUpdateArticle(ArticleVo articleVo) {
        Article article = BeanCopyUtils.copyBean(articleVo, Article.class);
        //保存分类
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getName, articleVo.getCategoryName()));
        if (Objects.isNull(category) && !ArticleStatusEnum.DRAFT.getStatus().equals(articleVo.getStatus())) {//分类不存在且文章不为草稿
            category = Category.builder().name(articleVo.getCategoryName()).build();
            categoryService.save(category);
        }

        //保存文章
        article.setCategoryId(category.getId());
        this.saveOrUpdate(article);

        //保存tag
        Long articleId = article.getId();
        //先删除关联表中所有文章的关联关系
        articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleId));
        List<String> tagNameList = new ArrayList<>(articleVo.getTagNameList());

        if (!CollectionUtils.isEmpty(tagNameList)) {
            saveOrUpdateTags(articleId, tagNameList);
        }
        return ResponseResult.okResult();
    }

    private void saveOrUpdateTags(Long articleId, List<String> tagNameList) {
        //查找已存在数据库中标签
        List<Tag> existTagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getName, tagNameList));
        //获取标签名
        List<String> existTagNameList = existTagList.stream().map(Tag::getName).collect(Collectors.toList());
        //获取标签id
        List<Long> existTagIdList = existTagList.stream().map(Tag::getId).collect(Collectors.toList());
        //根据标签名将已存在的标签名从文章标签列表中去除
        tagNameList.removeAll(existTagNameList);
        if (!CollectionUtils.isEmpty(tagNameList)) {
            //根据标签名生成对象
            List<Tag> tagList = tagNameList.stream().map(tagName -> Tag.builder().name(tagName).build()).collect(Collectors.toList());
            tagService.saveBatch(tagList);
            //合并原存在的标签id和后添加的标签id
            existTagIdList.addAll(tagList.stream().map(Tag::getId).collect(Collectors.toList()));
            //生成关联表对象
            List<ArticleTag> articleTagList = existTagIdList.stream().map(tagId -> ArticleTag.builder().articleId(articleId).tagId(tagId).build()).collect(Collectors.toList());
            articleTagService.saveBatch(articleTagList);
        }
    }

    //    根据id查询文章
    @Override
    public ResponseResult getArticleById(Long id) {
        ArticleDTO articleDTO = articleMapper.getArticleById(id);
        if (Objects.isNull(articleDTO)) {
//            throw new SystemException("文章不存在");
        }
//        浏览量++
        articleDTO.setViewCount(redisCache.incrementMapV(SystemConstants.REDIS_ARTICLE_VIEW_COUNT, String.valueOf(id), 1l));
        //当前用户是否点赞
        Long userId = SecurityUtils.getUserId();
        if (Objects.nonNull(userId)) {
            LambdaQueryWrapper<Like> eq = new LambdaQueryWrapper<Like>().eq(Like::getArticleId, id).eq(Like::getCreateBy, userId);
            if (likeMapper.selectCount(eq) != 0) {
                articleDTO.setIsLiked(true);
            }
        }
        return ResponseResult.okResult(articleDTO);
    }

    @Override
    public ResponseResult getAdminArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleTag::getArticleId, article.getId());

        List<ArticleTag> articleTags = articleTagMapper.selectList(lambdaQueryWrapper);
        List<Long> tagList = articleTags.stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());
        ArticleDetailDTO articleDetailDTO = BeanCopyUtils.copyBean(article, ArticleDetailDTO.class);
        articleDetailDTO.setTagList(tagList);
        return ResponseResult.okResult(articleDetailDTO);
    }


    /**
     * 后台文章列表
     *
     * @param condition
     * @return
     */
    @Override
    public ResponseResult listAdminArticleByCondition(ConditionVO condition) {
        List<ArticlePreviewDTO> articleList = articleMapper.listArticleByCondition(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        Long count = articleMapper.countArticleByCondition(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        PageVo pageVo = new PageVo(articleList, count);

        return ResponseResult.okResult(pageVo);
    }


    /**
     * 条件查询
     *
     * @param condition
     * @return
     */
    @Override
    public ResponseResult listArticleByCondition(ConditionVO condition) {
        List<ArticlePreviewDTO> articleList = articleMapper.listArticleByCondition(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        Long count = articleMapper.countArticleByCondition(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        PageVo pageVo = new PageVo(articleList, count);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult listArticleArchive() {
        Page page = new Page(PageUtils.getCurrent(), PageUtils.getSize());
        Page archivePage = articleMapper.selectPage(page, new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getTitle, Article::getThumbnail, Article::getCreateTime)
                .eq(Article::getStatus, "1")
        );
        List<ArticleArchiveVO> articleArchiveVOList = BeanCopyUtils.copyBeanList(archivePage.getRecords(), ArticleArchiveVO.class);
        return ResponseResult.okResult(PageVo.builder().rows(articleArchiveVOList).total(page.getTotal()).build());
    }


}
