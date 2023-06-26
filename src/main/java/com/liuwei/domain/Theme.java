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
 * 主题表(Theme)实体类
 *
 * @author makejava
 * @since 2022-11-06 16:04:49
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Theme implements Serializable {
    private static final long serialVersionUID = 535480816050691173L;
    /**
     * 主题id
     */
    @TableId
    private Long id;
    /**
     * 主题名
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 背景图片
     */
    private String backgroundImage;
    /**
     * 头部样式
     */
    private String headerStyle;
    /**
     * 主体样式
     */
    private String contentStyle;
    /**
     * 页脚样式
     */
    private String footerStyle;
    
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

