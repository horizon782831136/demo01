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
 * 角色-权限关联表(RoleMenu)实体类
 *
 * @author makejava
 * @since 2022-11-06 16:04:16
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -29333923661742029L;
    /**
     * 关联id
     */
    @TableId
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    
    private Long menuId;
    
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

