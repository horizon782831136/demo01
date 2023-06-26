package com.liuwei.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(Menu)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:22
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = 487322379092895503L;
    /**
     * 权限id
     */
    @TableId
    private Long menuId;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限(sys:user:select)
     */
    private String authority;
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

