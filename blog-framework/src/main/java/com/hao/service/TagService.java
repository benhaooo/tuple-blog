package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.domain.ResponseResult;
import com.hao.domain.dto.TagQueryDTO;
import com.hao.domain.entity.Tag;


/**
 * 标签(Tag)表服务接口
 *
 * @author benhao
 * @since 2022-11-27 02:01:21
 */
public interface TagService extends IService<Tag> {
    ResponseResult listTag(Integer pageNum, Integer pageSize, TagQueryDTO tagQueryDTO);

    ResponseResult listAllTag();

    ResponseResult listAllTagVO();

    ResponseResult insertTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);
}
