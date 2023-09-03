package com.ciaociao.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类表
 * @TableName category
 */
@TableName(value ="category")
@Data//生成getter,setter等函数
@NoArgsConstructor //生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类名
     */
    private String name;
    /**
     * 父分类id，如果没有父分类为-1
     */
    private Long pid;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态0:正常,1禁用
     */
    private String status;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}