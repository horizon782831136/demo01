package com.liuwei.domain;

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
 * 音乐表(TblMusic)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:36
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Music implements Serializable {
    private static final long serialVersionUID = -80387919874690384L;
    /**
     * 音乐id
     */
    @TableId
    private Long musicId;
    /**
     * 音乐名
     */
    private String name;
    /**
     * 音乐路径
     */
    private String musicLoc;
    
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 0-可用， 1-删除
     */
    private Integer delFlag;

}

