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
 * 文章表(Article)表实体类
 *
 * @author benhao
 * @since 2022-11-27 13:11:32
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article")
public class Article  {
    @TableId
    private Long id;

    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
//    private String summary;
    //所属分类id
    private Long categoryId;
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //是否允许评论 1是，0否
    private String isComment;
    //文章类型 0原创 1转载 2翻译
    private String type;
    //原文链接
    private String originalUrl;
    //访问量
    private Long viewCount;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
