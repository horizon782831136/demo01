package com.liuwei.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:56:39
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 443373341601818126L;
    /**
     * 类别编号
     */
    @TableId
    private Long cateId;
    /**
     * 父类别编号，为0表示根节点
     */
    private Long parentId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别状态：1-可用；0-已弃用
     */
    private Integer status;
    /**
     * 类别排序
     */
    private Integer sortorder;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 创建者id
     */
    private Long createrId;
    /**
     * 修改者id
     */
    private Long updaterId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0-可用， 1-删除
     */
    private Integer delFlag;

}

