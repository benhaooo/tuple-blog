package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.TagDTO;
import com.hao.domain.dto.TagQueryDTO;
import com.hao.domain.entity.Tag;
import com.hao.domain.vo.PageVo;
import com.hao.domain.vo.TagVO;
import com.hao.mapper.ArticleTagMapper;
import com.hao.mapper.TagMapper;
import com.hao.service.ArticleTagService;
import com.hao.service.TagService;
import com.hao.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author benhao
 * @since 2022-11-27 02:01:21
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public ResponseResult listTag(Integer pageNum, Integer pageSize, TagQueryDTO tagQueryDTO) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagQueryDTO.getName()), Tag::getName, tagQueryDTO.getName());
        Page<Tag> page = new Page<Tag>()
                .setCurrent(pageNum)
                .setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult listAllTag() {
        List<TagDTO> tags = tagMapper.selectListTagDTO();
        return ResponseResult.okResult(tags);
    }

    @Override
    public ResponseResult listAllTagVO() {
        List<Tag> tags = tagMapper.selectList(null);
        List<TagVO> tagVOS = BeanCopyUtils.copyBeanList(tags, TagVO.class);
        return ResponseResult.okResult(tagVOS);
    }

    @Override
    public ResponseResult insertTag(Tag tag) {
        tagMapper.insert(tag);
        return ResponseResult.okResult();
    }


    @Override
    public ResponseResult deleteTag(Long id) {
        articleTagMapper.deleteByTagId(id);
        tagMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        Tag tag = tagMapper.selectById(id);
        return ResponseResult.okResult(tag);
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        tagMapper.updateById(tag);
        return ResponseResult.okResult();
    }


}
