package com.liuwei.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(TblRole)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:58:38
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 554459680213465982L;
    /**
     * 角色id
     */
    @TableId
    private Long roleId;
    /**
     * 角色名
     */
    private String name;
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

