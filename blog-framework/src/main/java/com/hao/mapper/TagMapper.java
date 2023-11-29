package com.hao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.domain.dto.TagDTO;
import com.hao.domain.entity.Tag;
import com.hao.domain.vo.TagVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 标签(Tag)表数据库访问层
 *
 * @author benhao
 * @since 2022-11-27 02:01:18
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {


    List<TagDTO> selectListTagDTO();
}
