package com.hao.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 分类表(Category)表实体类
 *
 * @author makejava
 * @since 2022-11-09 01:00:34
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category {
    @TableId
    private Long id;

    //分类名
    private String name;
    //父分类id，如果没有父分类为-1
    private Long pid;
    //描述
    private String description;
    //状态0:正常,1禁用
    private String status;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
