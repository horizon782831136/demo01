package com.liuwei.domain;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2022-11-05 21:33:59
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -34342902183850157L;
    /**
     * 用户编号
     */
    @TableId
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码(加密)
     */
    private String password;

    private Integer gender;
    /**
     * 用户电话号码
     */

    private String telphone;
    /**
     * 重置密码的问题
     */

    private String question;
    /**
     * 重置密码的答案
     */

    private String answer;
    /**
     * 用户角色
     */

    private Integer role;
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

